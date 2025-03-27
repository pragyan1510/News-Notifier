package com.example.newsnotifier.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.newsnotifier.R

class NotificationHelper(private val context: Context){
    private val channelId = "news_notification_channel"

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "News Notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = context.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

        }
    }

    fun showNotification(newsTitle: String){
        val builder = NotificationCompat.Builder(context,channelId)
            .setSmallIcon(R.drawable.news)
            .setContentTitle("Latest News")
            .setContentText(newsTitle)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        NotificationManagerCompat.from(context).notify(1,builder.build())
    }
}