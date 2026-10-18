package com.example

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.GuardianDatabase
import com.example.data.preferences.AlertPreference
import com.example.data.preferences.GuardianPreferences
import com.example.data.preferences.HistoryPrivacyMode
import com.example.data.preferences.UserPreferencesRepository
import com.example.domain.model.AppItem
import com.example.domain.model.AppLanguage
import com.example.domain.model.DiagnosticReport
import com.example.domain.model.GuardianThemeMode
import com.example.domain.model.ProtectionState
import com.example.domain.model.ProtectionStatus
import com.example.notification.diagnostics.DiagnosticsEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ScreenRoute {
    LANGUAGE_SELECT,
    PERMISSION_SETUP,
    HOME,
    APP_MANAGER,
    SETTINGS
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    val preferencesRepository = UserPreferencesRepository(context)
    val database = GuardianDatabase.getDatabase(context)

    val preferences: StateFlow<GuardianPreferences> = preferencesRepository.preferencesFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = GuardianPreferences()
        )

    private val _currentScreen = MutableStateFlow(ScreenRoute.HOME)
    val currentScreen: StateFlow<ScreenRoute> = _currentScreen.asStateFlow()

    private val _installedApps = MutableStateFlow<List<AppItem>>(emptyList())
    val installedApps: StateFlow<List<AppItem>> = _installedApps.asStateFlow()

    private val _isLoadingApps = MutableStateFlow(false)
    val isLoadingApps: StateFlow<Boolean> = _isLoadingApps.asStateFlow()

    private val _latestDiagnosticReport = MutableStateFlow<DiagnosticReport?>(null)
    val latestDiagnosticReport: StateFlow<DiagnosticReport?> = _latestDiagnosticReport.asStateFlow()

    // Real-time permission states
    private val _isListenerGranted = MutableStateFlow(DiagnosticsEngine.isNotificationListenerGranted(context))
    val isListenerGranted: StateFlow<Boolean> = _isListenerGranted.asStateFlow()

    private val _isPostNotificationGranted = MutableStateFlow(DiagnosticsEngine.isPostNotificationGranted(context))
    val isPostNotificationGranted: StateFlow<Boolean> = _isPostNotificationGranted.asStateFlow()

    private val _isBatteryExempt = MutableStateFlow(DiagnosticsEngine.isIgnoringBatteryOptimizations(context))
    val isBatteryExempt: StateFlow<Boolean> = _isBatteryExempt.asStateFlow()

    val protectionStatus: StateFlow<ProtectionStatus> = combine(
        preferences,
        _isListenerGranted,
        _isPostNotificationGranted,
        _isBatteryExempt,
        _installedApps
    ) { prefs, listenerGranted, notifGranted, batteryExempt, apps ->
        val monitoredCount = apps.count { it.isMonitored }
        val state = when {
            !prefs.monitoringActive -> ProtectionState.PAUSED
            !listenerGranted -> ProtectionState.ACTION_NEEDED
            !batteryExempt -> ProtectionState.BATTERY_RESTRICTED
            else -> ProtectionState.ACTIVE
        }

        ProtectionStatus(
            state = state,
            isListenerGranted = listenerGranted,
            isPostNotificationGranted = notifGranted,
            isIgnoringBatteryOptimizations = batteryExempt,
            isMonitoringActive = prefs.monitoringActive,
            monitoredAppsCount = if (apps.isEmpty()) 0 else monitoredCount
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = ProtectionStatus(
            state = ProtectionState.READY,
            isListenerGranted = false,
            isPostNotificationGranted = false,
            isIgnoringBatteryOptimizations = false,
            isMonitoringActive = true,
            monitoredAppsCount = 0
        )
    )

    init {
        // Evaluate initial navigation route based on whether setup was already completed
        viewModelScope.launch {
            preferencesRepository.preferencesFlow.collect { prefs ->
                if (!prefs.setupCompleted && _currentScreen.value == ScreenRoute.HOME) {
                    _currentScreen.value = ScreenRoute.LANGUAGE_SELECT
                }
            }
        }
        refreshSystemState()
        loadInstalledApps()
    }

    fun refreshSystemState() {
        _isListenerGranted.value = DiagnosticsEngine.isNotificationListenerGranted(context)
        _isPostNotificationGranted.value = DiagnosticsEngine.isPostNotificationGranted(context)
        _isBatteryExempt.value = DiagnosticsEngine.isIgnoringBatteryOptimizations(context)
    }

    fun loadInstalledApps() {
        viewModelScope.launch {
            _isLoadingApps.value = true
            withContext(Dispatchers.IO) {
                try {
                    val pm = context.packageManager
                    val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
                    val excluded = preferences.value.excludedPackages

                    val appList = packages.mapNotNull { appInfo ->
                        // Skip self
                        if (appInfo.packageName == context.packageName) return@mapNotNull null

                        val appName = pm.getApplicationLabel(appInfo).toString()
                        val icon = try {
                            pm.getApplicationIcon(appInfo)
                        } catch (_: Exception) {
                            null
                        }
                        val isSystem = (appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0

                        AppItem(
                            packageName = appInfo.packageName,
                            appName = appName,
                            icon = icon,
                            isSystemApp = isSystem,
                            isMonitored = !excluded.contains(appInfo.packageName)
                        )
                    }.sortedBy { it.appName.lowercase() }

                    _installedApps.value = appList
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    _isLoadingApps.value = false
                }
            }
        }
    }

    fun navigateTo(route: ScreenRoute) {
        _currentScreen.value = route
    }

    fun completeSetup() {
        viewModelScope.launch {
            preferencesRepository.setSetupCompleted(true)
            _currentScreen.value = ScreenRoute.HOME
        }
    }

    fun updateLanguage(language: AppLanguage) {
        viewModelScope.launch {
            preferencesRepository.setLanguage(language)
        }
    }

    fun updateTheme(themeMode: GuardianThemeMode) {
        viewModelScope.launch {
            preferencesRepository.setThemeMode(themeMode)
        }
    }

    fun updateReducedMotion(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setReducedMotion(enabled)
        }
    }

    fun updateHaptics(enabled: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setHapticFeedback(enabled)
        }
    }

    fun updateMonitoring(active: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setMonitoringActive(active)
        }
    }

    fun updateHistoryPrivacy(mode: HistoryPrivacyMode) {
        viewModelScope.launch {
            preferencesRepository.setHistoryPrivacyMode(mode)
        }
    }

    fun updateAlertPreference(pref: AlertPreference) {
        viewModelScope.launch {
            preferencesRepository.setAlertPreference(pref)
        }
    }

    fun updateRetentionDays(days: Int) {
        viewModelScope.launch {
            preferencesRepository.setRetentionDays(days)
        }
    }

    fun toggleAppMonitoring(packageName: String, isMonitored: Boolean) {
        viewModelScope.launch {
            preferencesRepository.setPackageMonitoring(packageName, isMonitored)
            _installedApps.value = _installedApps.value.map {
                if (it.packageName == packageName) it.copy(isMonitored = isMonitored) else it
            }
        }
    }

    fun runDiagnostics() {
        viewModelScope.launch {
            val report = DiagnosticsEngine.runDiagnostics(context)
            _latestDiagnosticReport.value = report
            preferencesRepository.updateLastDiagnostic(report.timestamp, report.summary)
            refreshSystemState()
        }
    }

    fun clearDiagnosticReport() {
        _latestDiagnosticReport.value = null
    }

    fun clearHistory() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                database.notificationEventDao().clearAllEvents()
            }
        }
    }

    fun resetPreferences() {
        viewModelScope.launch {
            preferencesRepository.resetToDefaults()
            loadInstalledApps()
        }
    }
}
