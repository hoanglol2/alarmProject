package com.example.alarm.receiver

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.alarm.R
import com.example.alarm.utils.Constants
import com.example.alarm.utils.FormatTime.convertDate
import com.example.alarm.utils.NotifyChannel
import com.example.alarm.utils.NotifyChannel.CHANNEL_ID
import com.example.alarm.utils.NotifyChannel.CHANNEL_NAME
import com.example.alarm.utils.NotifyChannel.DESCRIPTION_TEXT
import com.example.alarm.utils.RandomIntUtil


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)

        when (intent.action) {
            Constants.ACTION_SET_EXACT_ALARM -> {

                Log.d("MainActivity", "$timeInMillis")

                createNotificationChannel(context)
                sendNotification(
                    context,
                    "Nguyễn Duy Hoàng",
                    "Bạn hẹn giờ lúc ${convertDate(timeInMillis)}"
                )
            }
        }
    }

    private fun createNotificationChannel(
        context: Context
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
            channel.description = DESCRIPTION_TEXT
            val notificationManager: NotificationManager? = getSystemService(
                context,
                NotificationManager::class.java
            )
            notificationManager!!.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(context: Context, title: String, message: String) {
        val builder = NotificationCompat.Builder(context, NotifyChannel.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_message)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // more content message
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )

        with(NotificationManagerCompat.from(context)) {
            notify(
                RandomIntUtil.getRandomInt(),
                builder.build()
            )
        }
        // sound notification
        /*builder.setSound()*/
    }
}