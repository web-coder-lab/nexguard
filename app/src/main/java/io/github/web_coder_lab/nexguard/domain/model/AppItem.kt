package io.github.web_coder_lab.nexguard.domain.model

import android.graphics.drawable.Drawable

data class AppItem(
    val packageName: String,
    val appName: String,
    val icon: Drawable? = null,
    val isSystemApp: Boolean = false,
    val isMonitored: Boolean = true,
    val lastNotificationTime: Long? = null,
    val eventCount: Int = 0
)
