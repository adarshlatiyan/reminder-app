package com.adarsh.reminderapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.adarsh.reminderapp.data.ReminderModel

@Database(entities = [ReminderModel::class], version = 1)
abstract class AppDb : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}