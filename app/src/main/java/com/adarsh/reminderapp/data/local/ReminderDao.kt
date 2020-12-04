package com.adarsh.reminderapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.adarsh.reminderapp.data.ReminderModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert
    suspend fun insertReminder(reminder: ReminderModel)

    @Delete
    suspend fun deleteReminder(reminder: ReminderModel)

    @Query("SELECT * FROM reminder")
    fun getAllReminders() : Flow<List<ReminderModel>>
}