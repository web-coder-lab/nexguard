package io.github.web_coder_lab.nexguard

import android.app.Application
import io.github.web_coder_lab.nexguard.background.worker.WorkScheduler
import io.github.web_coder_lab.nexguard.notification.alerts.SmartAlertManager

class GuardianApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        try {
            SmartAlertManager.createNotificationChannel(this)
            WorkScheduler.schedulePeriodicDiagnostics(this)
        } catch (_: Exception) {
            // Safe fallback during tests or constrained environments
        }
    }
}
