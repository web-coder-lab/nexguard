package io.github.web_coder_lab.nexguard.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import io.github.web_coder_lab.nexguard.domain.model.AppLanguage
import io.github.web_coder_lab.nexguard.domain.model.GuardianThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.File
import java.io.IOException

val Context.guardianDataStore: DataStore<Preferences> by preferencesDataStore(name = "guardian_preferences")

class UserPreferencesRepository(private val context: Context) {

    private object PreferencesKeys {
        val KEY_LANGUAGE = stringPreferencesKey("language_code")
        val KEY_SETUP_COMPLETED = booleanPreferencesKey("setup_completed")
        val KEY_THEME_MODE = stringPreferencesKey("theme_mode")
        val KEY_REDUCED_MOTION = booleanPreferencesKey("reduced_motion")
        val KEY_HAPTIC_FEEDBACK = booleanPreferencesKey("haptic_feedback")
        val KEY_MONITORING_ACTIVE = booleanPreferencesKey("monitoring_active")
        val KEY_HISTORY_PRIVACY = stringPreferencesKey("history_privacy_mode")
        val KEY_ALERT_PREFERENCE = stringPreferencesKey("alert_preference")
        val KEY_RETENTION_DAYS = intPreferencesKey("retention_days")
        val KEY_EXCLUDED_PACKAGES = stringSetPreferencesKey("excluded_packages")
        val KEY_SYSTEM_DEFAULT_APPLIED = booleanPreferencesKey("system_default_applied")
        val KEY_LAST_DIAGNOSTIC_TS = longPreferencesKey("last_diagnostic_ts")
        val KEY_LAST_DIAGNOSTIC_SUMMARY = stringPreferencesKey("last_diagnostic_summary")
    }

    val preferencesFlow: Flow<GuardianPreferences> = context.guardianDataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val langCode = preferences[PreferencesKeys.KEY_LANGUAGE] ?: "en"
            val setupCompleted = preferences[PreferencesKeys.KEY_SETUP_COMPLETED] ?: false
            val themeStr = preferences[PreferencesKeys.KEY_THEME_MODE] ?: GuardianThemeMode.BLACK.name
            val themeMode = runCatching { GuardianThemeMode.valueOf(themeStr) }.getOrDefault(GuardianThemeMode.BLACK)
            val reducedMotion = preferences[PreferencesKeys.KEY_REDUCED_MOTION] ?: false
            val hapticFeedback = preferences[PreferencesKeys.KEY_HAPTIC_FEEDBACK] ?: true
            val monitoringActive = preferences[PreferencesKeys.KEY_MONITORING_ACTIVE] ?: true
            val privacyStr = preferences[PreferencesKeys.KEY_HISTORY_PRIVACY] ?: HistoryPrivacyMode.METADATA_ONLY.name
            val privacyMode = runCatching { HistoryPrivacyMode.valueOf(privacyStr) }.getOrDefault(HistoryPrivacyMode.METADATA_ONLY)
            val alertStr = preferences[PreferencesKeys.KEY_ALERT_PREFERENCE] ?: AlertPreference.CRITICAL_ONLY.name
            val alertPref = runCatching { AlertPreference.valueOf(alertStr) }.getOrDefault(AlertPreference.CRITICAL_ONLY)
            val retentionDays = preferences[PreferencesKeys.KEY_RETENTION_DAYS] ?: 7
            val excluded = preferences[PreferencesKeys.KEY_EXCLUDED_PACKAGES] ?: emptySet()
            val lastDiagTs = preferences[PreferencesKeys.KEY_LAST_DIAGNOSTIC_TS] ?: 0L
            val lastDiagSummary = preferences[PreferencesKeys.KEY_LAST_DIAGNOSTIC_SUMMARY] ?: ""

            GuardianPreferences(
                language = AppLanguage.fromCode(langCode),
                setupCompleted = setupCompleted,
                themeMode = themeMode,
                reducedMotion = reducedMotion,
                hapticFeedback = hapticFeedback,
                monitoringActive = monitoringActive,
                historyPrivacyMode = privacyMode,
                alertPreference = alertPref,
                retentionDays = retentionDays,
                excludedPackages = excluded,
                lastDiagnosticTimestamp = lastDiagTs,
                lastDiagnosticSummary = lastDiagSummary
            )
        }

    suspend fun setLanguage(language: AppLanguage) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_LANGUAGE] = language.code
        }
    }

    suspend fun setSetupCompleted(completed: Boolean) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_SETUP_COMPLETED] = completed
        }
    }

    suspend fun setThemeMode(mode: GuardianThemeMode) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_THEME_MODE] = mode.name
        }
    }

    suspend fun setReducedMotion(enabled: Boolean) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_REDUCED_MOTION] = enabled
        }
    }

    suspend fun setHapticFeedback(enabled: Boolean) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_HAPTIC_FEEDBACK] = enabled
        }
    }

    suspend fun setMonitoringActive(active: Boolean) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_MONITORING_ACTIVE] = active
        }
    }

    suspend fun setHistoryPrivacyMode(mode: HistoryPrivacyMode) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_HISTORY_PRIVACY] = mode.name
        }
    }

    suspend fun setAlertPreference(preference: AlertPreference) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_ALERT_PREFERENCE] = preference.name
        }
    }

    suspend fun setRetentionDays(days: Int) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_RETENTION_DAYS] = days
        }
    }

    suspend fun setPackageMonitoring(packageName: String, isMonitored: Boolean) {
        context.guardianDataStore.edit { preferences ->
            val current = preferences[PreferencesKeys.KEY_EXCLUDED_PACKAGES]?.toMutableSet() ?: mutableSetOf()
            if (isMonitored) {
                current.remove(packageName)
            } else {
                current.add(packageName)
            }
            preferences[PreferencesKeys.KEY_EXCLUDED_PACKAGES] = current
        }
    }

    suspend fun applySystemAppDefaultsIfNeeded(systemPackages: Set<String>) {
        val datastoreFile = File(context.filesDir, "datastore/guardian_preferences.preferences_pb")
        val existingInstall = datastoreFile.exists()
        context.guardianDataStore.edit { preferences ->
            if (preferences[PreferencesKeys.KEY_SYSTEM_DEFAULT_APPLIED] == true) return@edit
            if (!existingInstall) {
                preferences[PreferencesKeys.KEY_EXCLUDED_PACKAGES] = systemPackages
            }
            preferences[PreferencesKeys.KEY_SYSTEM_DEFAULT_APPLIED] = true
        }
    }

    suspend fun updateLastDiagnostic(timestamp: Long, summary: String) {
        context.guardianDataStore.edit { preferences ->
            preferences[PreferencesKeys.KEY_LAST_DIAGNOSTIC_TS] = timestamp
            preferences[PreferencesKeys.KEY_LAST_DIAGNOSTIC_SUMMARY] = summary
        }
    }

    suspend fun resetToDefaults() {
        context.guardianDataStore.edit { preferences ->
            preferences.remove(PreferencesKeys.KEY_THEME_MODE)
            preferences.remove(PreferencesKeys.KEY_REDUCED_MOTION)
            preferences.remove(PreferencesKeys.KEY_HAPTIC_FEEDBACK)
            preferences.remove(PreferencesKeys.KEY_MONITORING_ACTIVE)
            preferences.remove(PreferencesKeys.KEY_HISTORY_PRIVACY)
            preferences.remove(PreferencesKeys.KEY_ALERT_PREFERENCE)
            preferences.remove(PreferencesKeys.KEY_RETENTION_DAYS)
            preferences.remove(PreferencesKeys.KEY_EXCLUDED_PACKAGES)
            // Language & setupCompleted are kept so user doesn't lose their selected locale or onboarding state!
        }
    }
}
