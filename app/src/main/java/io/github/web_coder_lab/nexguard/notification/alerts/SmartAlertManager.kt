package io.github.web_coder_lab.nexguard.notification.alerts

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.github.web_coder_lab.nexguard.MainActivity
import io.github.web_coder_lab.nexguard.R
import io.github.web_coder_lab.nexguard.data.preferences.AlertPreference
import io.github.web_coder_lab.nexguard.notification.diagnostics.DiagnosticsEngine

object SmartAlertManager {

    private const val CHANNEL_ID = "nexguard_diagnostic_alerts"
    private const val NOTIFICATION_ID = 1001

    private var lastAlertTimestamp = 0L
    private const val COOLDOWN_MILLIS = 30 * 60 * 1000L // 30 minutes cooldown between alerts

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NEXGUARD Diagnostics"
            val descriptionText = "Important alerts when background notification delivery is restricted"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
                enableVibration(true)
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun maybePostDiagnosticAlert(
        context: Context,
        title: String,
        message: String,
        isCritical: Boolean,
        alertPreference: AlertPreference
    ) {
        if (alertPreference == AlertPreference.SILENT) return
        if (alertPreference == AlertPreference.CRITICAL_ONLY && !isCritical) return
        if (!DiagnosticsEngine.isPostNotificationGranted(context)) return

        val now = System.currentTimeMillis()
        if (now - lastAlertTimestamp < COOLDOWN_MILLIS) return
        lastAlertTimestamp = now

        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.guardian_icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(if (isCritical) NotificationCompat.PRIORITY_HIGH else NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
        } catch (_: SecurityException) {
            // Handled safely if permission was revoked
        }
    }
}
