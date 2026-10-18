package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.local.entity.NotificationEventEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationEventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: NotificationEventEntity): Long

    @Query("SELECT * FROM notification_events ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentEvents(limit: Int = 100): Flow<List<NotificationEventEntity>>

    @Query("SELECT * FROM notification_events WHERE packageName = :packageName ORDER BY timestamp DESC")
    fun getEventsForPackage(packageName: String): Flow<List<NotificationEventEntity>>

    @Query("SELECT COUNT(*) FROM notification_events")
    fun getEventCount(): Flow<Int>

    @Query("DELETE FROM notification_events WHERE timestamp < :cutoffTimestamp")
    suspend fun deleteEventsOlderThan(cutoffTimestamp: Long): Int

    @Query("DELETE FROM notification_events")
    suspend fun clearAllEvents(): Int

    @Query("SELECT * FROM notification_events WHERE packageName = :packageName ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestEventForPackage(packageName: String): NotificationEventEntity?
}
