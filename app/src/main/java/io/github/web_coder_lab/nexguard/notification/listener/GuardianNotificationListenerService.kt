package io.github.web_coder_lab.nexguard.notification.listener

import android.app.Notification
import android.content.pm.PackageManager
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import io.github.web_coder_lab.nexguard.data.local.GuardianDatabase
import io.github.web_coder_lab.nexguard.data.local.entity.NotificationEventEntity
import io.github.web_coder_lab.nexguard.data.preferences.HistoryPrivacyMode
import io.github.web_coder_lab.nexguard.data.preferences.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GuardianNotificationListenerService : NotificationListenerService() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var database: GuardianDatabase
    private lateinit var preferencesRepo: UserPreferencesRepository

    override fun onCreate() {
        super.onCreate()
        database = GuardianDatabase.getDatabase(applicationContext)
        preferencesRepo = UserPreferencesRepository(applicationContext)
        Log.d(TAG, "GuardianNotificationListenerService created")
    }

    override fun onListenerConnected() {
        super.onListenerConnected()
        ListenerStateHolder.setConnected(true)
        Log.i(TAG, "Notification listener connected successfully")
    }

    override fun onListenerDisconnected() {
        super.onListenerDisconnected()
        ListenerStateHolder.setConnected(false)
        Log.w(TAG, "Notification listener disconnected")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if (sbn == null) return

        val pkgName = sbn.packageName ?: return
        // Do not monitor our own diagnostic notifications
        if (pkgName == applicationContext.packageName) return

        serviceScope.launch {
            try {
                val prefs = preferencesRepo.preferencesFlow.first()
                if (!prefs.monitoringActive) return@launch
                if (prefs.excludedPackages.contains(pkgName)) return@launch

                ListenerStateHolder.recordEvent(sbn.postTime)

                val appName = try {
                    val appInfo = packageManager.getApplicationInfo(pkgName, 0)
                    packageManager.getApplicationLabel(appInfo).toString()
                } catch (e: PackageManager.NameNotFoundException) {
                    pkgName
                }

                val notification = sbn.notification
                val category = notification?.category
                val title = if (prefs.historyPrivacyMode == HistoryPrivacyMode.METADATA_AND_TITLE) {
                    notification?.extras?.getCharSequence(Notification.EXTRA_TITLE)?.toString()?.take(64)
                } else null

                val event = NotificationEventEntity(
                    packageName = pkgName,
                    appName = appName,
                    timestamp = sbn.postTime,
                    eventType = "POSTED",
                    category = category,
                    hasTitle = title != null,
                    title = title,
                    diagnosticStatus = "NORMAL"
                )

                database.notificationEventDao().insertEvent(event)
            } catch (e: Exception) {
                Log.e(TAG, "Error logging notification event", e)
            }
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        if (sbn == null) return

        val pkgName = sbn.packageName ?: return
        if (pkgName == applicationContext.packageName) return

        serviceScope.launch {
            try {
                val prefs = preferencesRepo.preferencesFlow.first()
                if (!prefs.monitoringActive || prefs.excludedPackages.contains(pkgName)) return@launch

                val appName = try {
                    val appInfo = packageManager.getApplicationInfo(pkgName, 0)
                    packageManager.getApplicationLabel(appInfo).toString()
                } catch (e: Exception) {
                    pkgName
                }

                val event = NotificationEventEntity(
                    packageName = pkgName,
                    appName = appName,
                    timestamp = System.currentTimeMillis(),
                    eventType = "REMOVED",
                    category = null,
                    hasTitle = false,
                    title = null,
                    diagnosticStatus = "NORMAL"
                )

                database.notificationEventDao().insertEvent(event)
            } catch (e: Exception) {
                Log.e(TAG, "Error logging notification removal", e)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ListenerStateHolder.setConnected(false)
        serviceScope.cancel()
        Log.d(TAG, "GuardianNotificationListenerService destroyed")
    }

    companion object {
        private const val TAG = "GuardianListener"
    }
}
