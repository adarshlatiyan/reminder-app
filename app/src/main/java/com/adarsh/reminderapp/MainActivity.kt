package com.adarsh.reminderapp

import android.app.AlarmManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val alarmManager by lazy { getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val pi = PendingIntent.getBroadcast(this, AlarmReceiver.REMINDER_REQUEST_CODE, Intent(this, AlarmReceiver::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, pi)
    }
}