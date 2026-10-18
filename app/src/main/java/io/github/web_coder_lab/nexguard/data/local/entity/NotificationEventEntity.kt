package io.github.web_coder_lab.nexguard.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_events")
data class NotificationEventEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val packageName: String,
    val appName: String,
    val timestamp: Long = System.currentTimeMillis(),
    val eventType: String, // "POSTED", "REMOVED"
    val category: String? = null,
    val hasTitle: Boolean = false,
    val title: String? = null, // only stored if user explicitly opted in
    val diagnosticStatus: String = "NORMAL"
)
