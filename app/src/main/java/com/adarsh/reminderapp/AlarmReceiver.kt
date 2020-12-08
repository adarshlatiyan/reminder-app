package com.adarsh.reminderapp

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.adarsh.reminderapp.data.local.ReminderDao
import com.adarsh.reminderapp.util.AlarmHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "AlarmReceiver"
    }

    @Inject lateinit var dao: ReminderDao

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ${intent?.getIntExtra(AlarmHelper.EXTRA_REMINDER_PK, -1)}")

        val notification = NotificationCompat.Builder(context!!, MyApp.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Test")
            .setContentText("Hello, this is a test")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(0, notification)
    }
}