package com.adarsh.reminderapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


fun getDateString(cal: Calendar): String {
    return SimpleDateFormat.getDateInstance().format(cal.timeInMillis)
}

@SuppressLint("SimpleDateFormat")
fun getTimeString(cal: Calendar): String {
    return SimpleDateFormat("hh:mm a").format(cal.time)
}