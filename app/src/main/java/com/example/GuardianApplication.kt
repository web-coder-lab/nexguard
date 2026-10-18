package com.example

import android.app.Application
import com.example.background.worker.WorkScheduler
import com.example.notification.alerts.SmartAlertManager

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
