package io.github.web_coder_lab.nexguard.data.preferences

import io.github.web_coder_lab.nexguard.domain.model.AppLanguage
import io.github.web_coder_lab.nexguard.domain.model.GuardianThemeMode

data class GuardianPreferences(
    val language: AppLanguage = AppLanguage.EN,
    val setupCompleted: Boolean = false,
    val themeMode: GuardianThemeMode = GuardianThemeMode.BLACK,
    val reducedMotion: Boolean = false,
    val hapticFeedback: Boolean = true,
    val monitoringActive: Boolean = true,
    val historyPrivacyMode: HistoryPrivacyMode = HistoryPrivacyMode.METADATA_ONLY,
    val alertPreference: AlertPreference = AlertPreference.CRITICAL_ONLY,
    val retentionDays: Int = 7,
    val excludedPackages: Set<String> = emptySet(),
    val lastDiagnosticTimestamp: Long = 0L,
    val lastDiagnosticSummary: String = ""
)

enum class HistoryPrivacyMode {
    METADATA_ONLY,
    METADATA_AND_TITLE
}

enum class AlertPreference {
    SILENT,
    CRITICAL_ONLY,
    ALL
}
