package com.adarsh.reminderapp.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "reminder")
data class ReminderModel(
    val title: String,
    val description: String,
    val timeInMillis: Long,
    @PrimaryKey(autoGenerate = true)
    var pk: Int = 0,
) : Parcelable
