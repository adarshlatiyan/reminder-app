package com.adarsh.reminderapp.data.local

import androidx.room.*
import com.adarsh.reminderapp.data.ReminderModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderModel)

    @Delete
    suspend fun deleteReminder(reminder: ReminderModel)

    @Query("SELECT * FROM reminder")
    fun getAllReminders() : Flow<List<ReminderModel>>
}