package com.adarsh.reminderapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adarsh.reminderapp.data.ReminderModel

@Database(entities = [ReminderModel::class], version = 1, exportSchema = false)
abstract class AppDb : RoomDatabase() {
    companion object {
        const val DB_NAME = "reminder_app_db"
    }
    abstract fun reminderDao(): ReminderDao
}