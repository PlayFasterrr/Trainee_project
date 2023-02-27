package com.example.treneeproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.treneeproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, BlankFragment.newInstance())
            .commit()
    }




}