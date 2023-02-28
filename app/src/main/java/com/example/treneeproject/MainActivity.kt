package com.example.treneeproject

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home -> openFragment(BlankFragment.newInstance())
            R.id.preferences -> openFragment(BlankFragment2.newInstance())
        }
        return true
    }
    private fun openFragment (fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.place_holder, fragment)
            .commit()
    }
}