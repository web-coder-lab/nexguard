package io.github.web_coder_lab.nexguard.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.github.web_coder_lab.nexguard.data.local.dao.NotificationEventDao
import io.github.web_coder_lab.nexguard.data.local.entity.NotificationEventEntity

@Database(entities = [NotificationEventEntity::class], version = 1, exportSchema = false)
abstract class GuardianDatabase : RoomDatabase() {

    abstract fun notificationEventDao(): NotificationEventDao

    companion object {
        @Volatile
        private var INSTANCE: GuardianDatabase? = null

        fun getDatabase(context: Context): GuardianDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GuardianDatabase::class.java,
                    "guardian_database"
                )
                    .fallbackToDestructiveMigration(dropAllTables = false)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
