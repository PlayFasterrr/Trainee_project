package com.example.treneeproject.musicplayer

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.treneeproject.R
import com.example.treneeproject.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment(), ServiceConnection {

    companion object {
        private var currentSong = mutableListOf(R.raw.blink_182_edging)
        private var musicService: MusicService? = null

        @JvmStatic
        fun newInstance() = PlayerFragment()
    }

    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPlayerBinding.inflate(inflater)

        controlSound(currentSong[0])

        val intent = Intent(this.context, MusicService::class.java)
        activity?.bindService(intent, this, AppCompatActivity.BIND_AUTO_CREATE)
        activity?.startService(intent)

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        musicService!!.showPlayerNotification()
    }
    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this.context, MusicService::class.java)
        musicService!!.player?.stop()
        musicService!!.player?.reset()
        musicService!!.player?.release()
        musicService!!.player = null
        activity?.stopService(intent)
    }

    private fun controlSound(id: Int) {

        binding.apply {
            fabPlay.setOnClickListener {
                if (musicService!!.player == null) {
                    musicService!!.player = MediaPlayer.create(this@PlayerFragment.context, id)
                    initializeSeekBar()
                }
                musicService!!.player?.start()
            }

            fabPause.setOnClickListener {
                if (musicService!!.player !== null) musicService!!.player?.pause()
            }

            fabStop.setOnClickListener {
                if (musicService!!.player !== null) {
                    musicService!!.player?.stop()
                    musicService!!.player?.reset()
                    musicService!!.player?.release()
                    musicService!!.player = null
                }
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    position: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) musicService!!.player?.seekTo(position)
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {}
            })

        }

    }

    private fun initializeSeekBar() {
        binding.seekBar.max = musicService!!.player!!.duration
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                try {
                    binding.seekBar.progress = musicService!!.player!!.currentPosition
                    handler.postDelayed(this, 1000)
                } catch (e: java.lang.Exception) {
                    binding.seekBar.progress = 0
                }
            }
        }, 0)
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        val binder = service as MusicService.MyBinder
        musicService = binder.currentService()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        musicService = null
    }
}