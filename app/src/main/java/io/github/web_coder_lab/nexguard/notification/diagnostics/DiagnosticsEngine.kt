package io.github.web_coder_lab.nexguard.notification.diagnostics

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.PowerManager
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import io.github.web_coder_lab.nexguard.domain.model.DiagnosticActionType
import io.github.web_coder_lab.nexguard.domain.model.DiagnosticItem
import io.github.web_coder_lab.nexguard.domain.model.DiagnosticReport
import io.github.web_coder_lab.nexguard.domain.model.DiagnosticSeverity
import io.github.web_coder_lab.nexguard.notification.listener.ListenerStateHolder

object DiagnosticsEngine {

    fun isNotificationListenerGranted(context: Context): Boolean {
        val enabledPackages = NotificationManagerCompat.getEnabledListenerPackages(context)
        return enabledPackages.contains(context.packageName)
    }

    fun isPostNotificationGranted(context: Context): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            NotificationManagerCompat.from(context).areNotificationsEnabled()
        }
    }

    fun isIgnoringBatteryOptimizations(context: Context): Boolean {
        val powerManager = context.getSystemService(Context.POWER_SERVICE) as? PowerManager
        return powerManager?.isIgnoringBatteryOptimizations(context.packageName) ?: false
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager ?: return false
        val network = cm.activeNetwork ?: return false
        val capabilities = cm.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun runDiagnostics(context: Context): DiagnosticReport {
        val items = mutableListOf<DiagnosticItem>()
        var highestSeverity = DiagnosticSeverity.GOOD

        // 1. Notification Listener Access
        val isListenerGranted = isNotificationListenerGranted(context)
        if (!isListenerGranted) {
            highestSeverity = DiagnosticSeverity.ALERT
            items.add(
                DiagnosticItem(
                    id = "listener_permission",
                    title = "Notification Access Missing",
                    description = "NexGuard cannot monitor notifications from your installed apps without Android notification access.",
                    severity = DiagnosticSeverity.ALERT,
                    actionType = DiagnosticActionType.OPEN_NOTIFICATION_LISTENER
                )
            )
        } else {
            items.add(
                DiagnosticItem(
                    id = "listener_permission",
                    title = "Notification Access Granted",
                    description = "Android notification listener service access is enabled.",
                    severity = DiagnosticSeverity.GOOD
                )
            )
        }

        // 2. Listener Service Connection
        val isServiceConnected = ListenerStateHolder.isConnected.value
        if (isListenerGranted && !isServiceConnected) {
            if (highestSeverity != DiagnosticSeverity.ALERT) highestSeverity = DiagnosticSeverity.WARNING
            items.add(
                DiagnosticItem(
                    id = "listener_connection",
                    title = "Listener Connection Idle",
                    description = "Android has granted access, but the background service connection is currently initializing.",
                    severity = DiagnosticSeverity.WARNING,
                    actionType = DiagnosticActionType.OPEN_NOTIFICATION_LISTENER
                )
            )
        } else if (isServiceConnected) {
            items.add(
                DiagnosticItem(
                    id = "listener_connection",
                    title = "Listener Connected",
                    description = "Live connection active for notification callbacks.",
                    severity = DiagnosticSeverity.GOOD
                )
            )
        }

        // 3. Battery Optimization
        val isIgnoringBattery = isIgnoringBatteryOptimizations(context)
        if (!isIgnoringBattery) {
            if (highestSeverity == DiagnosticSeverity.GOOD) highestSeverity = DiagnosticSeverity.WARNING
            items.add(
                DiagnosticItem(
                    id = "battery_optimization",
                    title = "Battery Optimization Active",
                    description = "Device may pause background notification checks during deep sleep (Doze mode).",
                    severity = DiagnosticSeverity.WARNING,
                    actionType = DiagnosticActionType.OPEN_BATTERY_SETTINGS
                )
            )
        } else {
            items.add(
                DiagnosticItem(
                    id = "battery_optimization",
                    title = "Battery Exemption Active",
                    description = "Unrestricted background execution enabled for NexGuard.",
                    severity = DiagnosticSeverity.GOOD
                )
            )
        }

        // 4. NexGuard Diagnostic Alerts
        val isAlertGranted = isPostNotificationGranted(context)
        if (!isAlertGranted) {
            items.add(
                DiagnosticItem(
                    id = "post_notifications",
                    title = "Diagnostic Alerts Disabled",
                    description = "NexGuard will not be able to send you notification status alerts.",
                    severity = DiagnosticSeverity.INFO,
                    actionType = DiagnosticActionType.OPEN_NOTIFICATION_PERMISSION
                )
            )
        } else {
            items.add(
                DiagnosticItem(
                    id = "post_notifications",
                    title = "Diagnostic Alerts Enabled",
                    description = "NexGuard can notify you of critical background delivery disruptions.",
                    severity = DiagnosticSeverity.GOOD
                )
            )
        }

        // 5. Network Connectivity
        val isNetwork = isNetworkConnected(context)
        if (!isNetwork) {
            if (highestSeverity != DiagnosticSeverity.ALERT) highestSeverity = DiagnosticSeverity.WARNING
            items.add(
                DiagnosticItem(
                    id = "network_state",
                    title = "No Internet Connection",
                    description = "Third-party apps cannot receive push notifications without active internet.",
                    severity = DiagnosticSeverity.WARNING,
                    actionType = DiagnosticActionType.CHECK_NETWORK
                )
            )
        } else {
            items.add(
                DiagnosticItem(
                    id = "network_state",
                    title = "Network Connected",
                    description = "Internet connectivity is active.",
                    severity = DiagnosticSeverity.GOOD
                )
            )
        }

        // 6. Manufacturer background restriction note
        val mfgAdvice = ManufacturerHelper.getManufacturerAdvice()
        items.add(
            DiagnosticItem(
                id = "oem_guidance",
                title = "${ManufacturerHelper.getManufacturerName()} Optimization",
                description = mfgAdvice,
                severity = DiagnosticSeverity.INFO,
                actionType = DiagnosticActionType.OPEN_OEM_AUTOSTART
            )
        )

        val summary = when (highestSeverity) {
            DiagnosticSeverity.GOOD -> "All reliability checks passed successfully."
            DiagnosticSeverity.WARNING -> "Minor background restrictions detected that may delay notifications."
            DiagnosticSeverity.ALERT -> "Critical permissions missing. Notification monitoring is inactive."
            DiagnosticSeverity.INFO -> "Diagnostics complete."
        }

        val recommendation = when {
            !isListenerGranted -> "Grant notification access in Android Settings to activate protection."
            !isIgnoringBattery -> "Allow unrestricted background battery usage in Settings."
            else -> "System is optimized for reliable notification monitoring."
        }

        return DiagnosticReport(
            overallStatus = highestSeverity,
            items = items,
            manufacturerName = ManufacturerHelper.getManufacturerName(),
            androidVersion = Build.VERSION.SDK_INT,
            summary = summary,
            recommendation = recommendation
        )
    }
}
