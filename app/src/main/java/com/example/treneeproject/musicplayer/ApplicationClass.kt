package com.example.treneeproject.musicplayer

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class ApplicationClass: Application() {
    companion object{
        const val CHANNEL_ID_PLAYER = "channel1"
        const val CHANNEL_ID_RW = "channel2"
//        const val PLAY = "play"
//        const val PAUSE = "pause"
//        const val STOP = "stop"
//        const val EXIT= "exit"
    }

    override fun onCreate() {

        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationChannelPlayer = NotificationChannel(
                CHANNEL_ID_PLAYER,
                "Now playing song",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannelPlayer.description = "channel for showing song (very important!)"

            val notificationChannelRW = NotificationChannel(
                CHANNEL_ID_RW,
                "Items count",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannelRW.description = "channel for showing items count"


            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannelPlayer)
            notificationManager.createNotificationChannel(notificationChannelRW)
        }
    }
}