package com.adarsh.reminderapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "reminder")
data class ReminderModel(
    var title: String,
    var description: String,
    var timeInMillis: Long,
    @PrimaryKey(autoGenerate = true)
    var pk: Int = 0,
) : Parcelable
