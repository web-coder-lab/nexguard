package io.github.web_coder_lab.nexguard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.web_coder_lab.nexguard.ui.appmanager.AppManagerScreen
import io.github.web_coder_lab.nexguard.ui.diagnostics.DiagnosticsDialog
import io.github.web_coder_lab.nexguard.ui.home.HomeScreen
import io.github.web_coder_lab.nexguard.ui.onboarding.LanguageSelectionScreen
import io.github.web_coder_lab.nexguard.ui.onboarding.PermissionSetupScreen
import io.github.web_coder_lab.nexguard.ui.settings.SettingsScreen
import android.widget.Toast
import io.github.web_coder_lab.nexguard.localization.AppStrings
import io.github.web_coder_lab.nexguard.ui.theme.GuardianTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()
            val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()
            val protectionStatus by viewModel.protectionStatus.collectAsStateWithLifecycle()
            val installedApps by viewModel.installedApps.collectAsStateWithLifecycle()
            val isListenerGranted by viewModel.isListenerGranted.collectAsStateWithLifecycle()
            val isNotifGranted by viewModel.isPostNotificationGranted.collectAsStateWithLifecycle()
            val isBatteryExempt by viewModel.isBatteryExempt.collectAsStateWithLifecycle()
            val diagnosticReport by viewModel.latestDiagnosticReport.collectAsStateWithLifecycle()

            // Observe lifecycle to refresh permissions whenever user returns from Android Settings
            DisposableEffect(Unit) {
                val observer = LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_RESUME) {
                        viewModel.refreshSystemState()
                    }
                }
                lifecycle.addObserver(observer)
                onDispose {
                    lifecycle.removeObserver(observer)
                }
            }

            val layoutDirection = if (preferences.language.isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
                GuardianTheme(themeMode = preferences.themeMode) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        when (currentScreen) {
                            ScreenRoute.LANGUAGE_SELECT -> {
                                LanguageSelectionScreen(
                                    currentLanguage = preferences.language,
                                    onLanguageSelected = { viewModel.updateLanguage(it) },
                                    onContinue = { viewModel.navigateTo(ScreenRoute.PERMISSION_SETUP) }
                                )
                            }
                            ScreenRoute.PERMISSION_SETUP -> {
                                PermissionSetupScreen(
                                    currentLanguage = preferences.language,
                                    isListenerGranted = isListenerGranted,
                                    isNotificationGranted = isNotifGranted,
                                    isBatteryOptimizedExempt = isBatteryExempt,
                                    onBack = { viewModel.navigateTo(ScreenRoute.LANGUAGE_SELECT) },
                                    onRefreshPermissions = { viewModel.refreshSystemState() },
                                    onCompleteSetup = { viewModel.completeSetup() }
                                )
                            }
                            ScreenRoute.HOME -> {
                                HomeScreen(
                                    status = protectionStatus,
                                    currentLanguage = preferences.language,
                                    reducedMotion = preferences.reducedMotion,
                                    hapticEnabled = preferences.hapticFeedback,
                                    onNavigateToSettings = { viewModel.navigateTo(ScreenRoute.SETTINGS) },
                                    onRunDiagnostics = { viewModel.runDiagnostics() },
                                    onFixPermissions = { viewModel.navigateTo(ScreenRoute.PERMISSION_SETUP) },
                                    onStartAndMinimize = {
                                        viewModel.updateMonitoring(true)
                                        android.widget.Toast.makeText(
                                            this@MainActivity,
                                            AppStrings.get(preferences.language).protectionStartedNotice,
                                            android.widget.Toast.LENGTH_SHORT
                                        ).show()
                                        moveTaskToBack(true)
                                    },
                                    onStopProtection = {
                                        viewModel.updateMonitoring(false)
                                    }
                                )
                            }
                            ScreenRoute.APP_MANAGER -> {
                                AppManagerScreen(
                                    apps = installedApps,
                                    currentLanguage = preferences.language,
                                    onBack = { viewModel.navigateTo(ScreenRoute.SETTINGS) },
                                    onToggleMonitoring = { pkg, isMonitored ->
                                        viewModel.toggleAppMonitoring(pkg, isMonitored)
                                    }
                                )
                            }
                            ScreenRoute.SETTINGS -> {
                                SettingsScreen(
                                    preferences = preferences,
                                    onBack = { viewModel.navigateTo(ScreenRoute.HOME) },
                                    onUpdateTheme = { viewModel.updateTheme(it) },
                                    onUpdateLanguage = { viewModel.updateLanguage(it) },
                                    onUpdateReducedMotion = { viewModel.updateReducedMotion(it) },
                                    onUpdateHaptics = { viewModel.updateHaptics(it) },
                                    onUpdateMonitoring = { viewModel.updateMonitoring(it) },
                                    onUpdateHistoryPrivacy = { viewModel.updateHistoryPrivacy(it) },
                                    onUpdateAlertPreference = { viewModel.updateAlertPreference(it) },
                                    onUpdateRetentionDays = { viewModel.updateRetentionDays(it) },
                                    onNavigateToAppManager = { viewModel.navigateTo(ScreenRoute.APP_MANAGER) },
                                    onRunDiagnostics = { viewModel.runDiagnostics() },
                                    onClearHistory = { viewModel.clearHistory() },
                                    onResetPreferences = { viewModel.resetPreferences() }
                                )
                            }
                        }

                        // Diagnostics Dialog Overlay
                        diagnosticReport?.let { report ->
                            DiagnosticsDialog(
                                report = report,
                                currentLanguage = preferences.language,
                                onDismiss = { viewModel.clearDiagnosticReport() },
                                onReRun = { viewModel.runDiagnostics() }
                            )
                        }
                    }
                }
            }
        }
    }
}
