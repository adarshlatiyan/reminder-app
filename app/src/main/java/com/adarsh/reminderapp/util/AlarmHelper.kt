package com.adarsh.reminderapp.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.adarsh.reminderapp.AlarmReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject


class AlarmHelper @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        private const val TAG = "AlarmHelper"

        const val EXTRA_REMINDER_PK = "reminder_pk"
    }

    private val alarmManager by lazy { context.getSystemService(Context.ALARM_SERVICE) as AlarmManager }

    fun setAlarm(calendar: Calendar, pk: Int) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(EXTRA_REMINDER_PK, pk)
        }
        val pi = PendingIntent.getBroadcast(context, pk, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis - 1000,
                pi
            )
        else alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis - 1000,
            pi
        )


//        RESTART ALARM IF DEVICE IS REBOOTED

//        val receiver = ComponentName(context, BootReceiver::class.java)
//        val pm = context.packageManager
//        pm.setComponentEnabledSetting(
//            receiver,
//            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//            PackageManager.DONT_KILL_APP
//        )
    }

    fun cancelAlarm(pk: Int) {
        val pi = PendingIntent.getBroadcast(
            context,
            pk,
            Intent(context, AlarmReceiver::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.cancel(pi)
    }
}