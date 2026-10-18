package com.example.notification.diagnostics

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings

object ManufacturerHelper {

    enum class Manufacturer {
        SAMSUNG,
        XIAOMI,
        OPPO,
        VIVO,
        ONEPLUS,
        HUAWEI,
        PIXEL,
        MOTOROLA,
        OTHER
    }

    fun getDeviceManufacturer(): Manufacturer {
        val mfg = Build.MANUFACTURER.lowercase()
        return when {
            mfg.contains("samsung") -> Manufacturer.SAMSUNG
            mfg.contains("xiaomi") || mfg.contains("redmi") || mfg.contains("poco") -> Manufacturer.XIAOMI
            mfg.contains("oppo") || mfg.contains("realme") -> Manufacturer.OPPO
            mfg.contains("vivo") || mfg.contains("iqoo") -> Manufacturer.VIVO
            mfg.contains("oneplus") -> Manufacturer.ONEPLUS
            mfg.contains("huawei") || mfg.contains("honor") -> Manufacturer.HUAWEI
            mfg.contains("google") -> Manufacturer.PIXEL
            mfg.contains("motorola") || mfg.contains("moto") -> Manufacturer.MOTOROLA
            else -> Manufacturer.OTHER
        }
    }

    fun getManufacturerName(): String = Build.MANUFACTURER.replaceFirstChar { it.uppercase() }

    fun getManufacturerAdvice(): String {
        return when (getDeviceManufacturer()) {
            Manufacturer.SAMSUNG ->
                "Samsung One UI: In Device Care > Battery > Background usage limits, add important messaging apps to 'Never sleeping apps' and enable 'Allow background activity'."
            Manufacturer.XIAOMI ->
                "Xiaomi / MIUI / HyperOS: Open Security app > Manage Apps > Permissions > Autostart. Enable Autostart for messaging apps and set Battery Saver to 'No restrictions'."
            Manufacturer.OPPO ->
                "OPPO / Realme ColorOS: In Settings > Battery > App Battery Management, enable 'Allow foreground activity' and 'Allow background activity'."
            Manufacturer.VIVO ->
                "Vivo / Funtouch OS: In Settings > Battery > High background power consumption, toggle ON for your communication apps."
            Manufacturer.ONEPLUS ->
                "OnePlus OxygenOS: In Settings > Apps > App Battery Usage, select 'Unrestricted' and enable 'Allow background activity'."
            Manufacturer.HUAWEI ->
                "Huawei EMUI / HarmonyOS: In Settings > Battery > App Launch, switch manage automatically to 'Manage manually' and allow Auto-launch & Run in background."
            Manufacturer.PIXEL ->
                "Google Pixel: In Settings > Apps > App Battery Usage, ensure apps needing instant notifications are set to 'Unrestricted' or 'Optimized'."
            Manufacturer.MOTOROLA ->
                "Motorola: Set App Battery usage to 'Unrestricted' in App Info for sensitive messaging applications."
            Manufacturer.OTHER ->
                "Ensure your device battery saver does not restrict background activity for critical messaging applications."
        }
    }

    fun openManufacturerAutostartSettings(context: Context): Boolean {
        val intents = listOf(
            // Xiaomi
            Intent().setComponent(ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            // Samsung
            Intent().setComponent(ComponentName("com.samsung.android.lool", "com.samsung.android.sm.battery.ui.BatteryActivity")),
            Intent().setComponent(ComponentName("com.samsung.android.sm", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            // Oppo
            Intent().setComponent(ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            Intent().setComponent(ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            // Vivo
            Intent().setComponent(ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            Intent().setComponent(ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity")),
            // Huawei
            Intent().setComponent(ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            Intent().setComponent(ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity"))
        )

        for (intent in intents) {
            try {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                    return true
                }
            } catch (_: Exception) { }
        }

        // Fallback to standard app info or battery settings
        return try {
            val fallback = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            fallback.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(fallback)
            true
        } catch (_: Exception) {
            val appDetails = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                data = Uri.fromParts("package", context.packageName, null)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(appDetails)
            true
        }
    }
}
