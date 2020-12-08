package com.adarsh.reminderapp.util

import java.text.SimpleDateFormat
import java.util.*


fun getDateString(cal: Calendar): String {
    return SimpleDateFormat.getDateInstance().format(cal.timeInMillis)
}

fun getTimeString(cal: Calendar): String {
    return SimpleDateFormat.getTimeInstance().format(cal.time)
}