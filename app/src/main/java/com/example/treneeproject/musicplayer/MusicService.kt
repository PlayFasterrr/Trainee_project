package com.example.treneeproject.musicplayer

import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.example.treneeproject.R

class MusicService : Service() {

    private var musicBinder = MyBinder()
    var player: MediaPlayer? = null
    private lateinit var mediaSession : MediaSessionCompat

    override fun onBind(intent: Intent?): IBinder {
        mediaSession = MediaSessionCompat(baseContext, "Music")
        return musicBinder
    }

    inner class MyBinder : Binder() {
        fun currentService(): MusicService {
            return this@MusicService
        }
    }

    fun showPlayerNotification(){
        val playerForeground = NotificationCompat
            .Builder(baseContext, ApplicationClass.CHANNEL_ID_PLAYER)
            .setContentTitle("blink-182 - EDGING")
            .setContentText("pop-punk")
            .setSmallIcon(R.drawable.ic_play)
            .setLargeIcon(BitmapFactory.decodeResource(resources,
                R.drawable.blink_182_edging_album_cover
            ))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setOngoing(true)
            .addAction(R.drawable.ic_pause, "pause", null)
            .addAction(R.drawable.ic_play,"play", null)
            .addAction(R.drawable.ic_stop, "stop", null)
            .build()

        startForeground(29, playerForeground)
    }

}