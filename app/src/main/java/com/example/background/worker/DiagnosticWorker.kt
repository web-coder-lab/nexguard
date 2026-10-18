package com.example.background.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.data.local.GuardianDatabase
import com.example.data.preferences.UserPreferencesRepository
import com.example.notification.alerts.SmartAlertManager
import com.example.notification.diagnostics.DiagnosticsEngine
import kotlinx.coroutines.flow.first

class DiagnosticWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val prefsRepo = UserPreferencesRepository(appContext)
            val prefs = prefsRepo.preferencesFlow.first()

            // 1. Run lightweight diagnostic check
            val report = DiagnosticsEngine.runDiagnostics(appContext)
            prefsRepo.updateLastDiagnostic(report.timestamp, report.summary)

            // 2. Housekeeping: delete expired events based on retention policy
            val database = GuardianDatabase.getDatabase(appContext)
            val retentionMillis = prefs.retentionDays * 24L * 60L * 60L * 1000L
            val cutoff = System.currentTimeMillis() - retentionMillis
            database.notificationEventDao().deleteEventsOlderThan(cutoff)

            // 3. Alert if critical permission missing
            val isListenerGranted = DiagnosticsEngine.isNotificationListenerGranted(appContext)
            if (!isListenerGranted && prefs.monitoringActive) {
                SmartAlertManager.maybePostDiagnosticAlert(
                    context = appContext,
                    title = "NexGuard: Notification Access Revoked",
                    message = "Notification listener permission is required to monitor delivery.",
                    isCritical = true,
                    alertPreference = prefs.alertPreference
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}
