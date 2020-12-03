package com.adarsh.reminderapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder")
data class ReminderModel(
    val title: String,
    val description: String,
    val timeInMillis: Long,
    @PrimaryKey(autoGenerate = true)
    private val pk: Int = 0,
)
