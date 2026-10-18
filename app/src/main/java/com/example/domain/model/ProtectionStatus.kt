package com.example.domain.model

enum class NexGuardThemeMode {
    BLACK,
    WHITE,
    GOLDEN_BLACK,
    GOLDEN_WHITE;

    companion object {
        fun fromName(name: String): NexGuardThemeMode {
            return entries.firstOrNull { it.name.equals(name, ignoreCase = true) } ?: BLACK
        }
    }
}

typealias GuardianThemeMode = NexGuardThemeMode

enum class ProtectionState {
    READY,
    ACTIVE,
    ACTION_NEEDED,
    BATTERY_RESTRICTED,
    PAUSED,
    DISCONNECTED
}

data class ProtectionStatus(
    val state: ProtectionState,
    val isListenerGranted: Boolean,
    val isPostNotificationGranted: Boolean,
    val isIgnoringBatteryOptimizations: Boolean,
    val isMonitoringActive: Boolean,
    val monitoredAppsCount: Int,
    val lastCheckTimeMillis: Long = System.currentTimeMillis()
)
