package io.github.web_coder_lab.nexguard.domain.model

enum class DiagnosticSeverity {
    GOOD,
    WARNING,
    ALERT,
    INFO
}

data class DiagnosticItem(
    val id: String,
    val title: String,
    val description: String,
    val severity: DiagnosticSeverity,
    val actionType: DiagnosticActionType = DiagnosticActionType.NONE
)

enum class DiagnosticActionType {
    NONE,
    OPEN_NOTIFICATION_LISTENER,
    OPEN_NOTIFICATION_PERMISSION,
    OPEN_BATTERY_SETTINGS,
    OPEN_OEM_AUTOSTART,
    CHECK_NETWORK
}

data class DiagnosticReport(
    val timestamp: Long = System.currentTimeMillis(),
    val overallStatus: DiagnosticSeverity,
    val items: List<DiagnosticItem>,
    val manufacturerName: String,
    val androidVersion: Int,
    val summary: String,
    val recommendation: String
)
