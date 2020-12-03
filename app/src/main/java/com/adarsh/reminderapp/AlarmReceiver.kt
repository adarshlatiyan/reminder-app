package com.adarsh.reminderapp

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class AlarmReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "AlarmReceiver"

        const val REMINDER_REQUEST_CODE = 1000
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ")
        val notification = NotificationCompat.Builder(context!!, MyApp.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Test")
            .setContentText("Hello, this is a test")
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        manager.notify(0, notification)
    }
}