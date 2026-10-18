package io.github.web_coder_lab.nexguard.background.recovery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import io.github.web_coder_lab.nexguard.background.worker.WorkScheduler
import io.github.web_coder_lab.nexguard.notification.alerts.SmartAlertManager

class BootCompletedReceiver : BroadcastReceiver {
    // Required default zero-argument constructor for Android OS manifest instantiation
    constructor() : super()

    // Test constructor allowing custom scheduler
    var testActionHook: (() -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) return
        val action = intent.action
        Log.d("BootReceiver", "Received broadcast: $action")
        if (action == Intent.ACTION_BOOT_COMPLETED || action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            SmartAlertManager.createNotificationChannel(context)
            WorkScheduler.schedulePeriodicDiagnostics(context)
            testActionHook?.invoke()
        }
    }
}
