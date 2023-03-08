package com.example.treneeproject.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.treneeproject.ItemAdapter
import com.example.treneeproject.R
import com.example.treneeproject.databinding.FragmentRecyclerViewBinding
import com.example.treneeproject.musicplayer.ApplicationClass
import com.example.treneeproject.recyclerview.dataclsses.Figure
import com.example.treneeproject.recyclerview.dataclsses.SomeText

class RecyclerViewFragment : Fragment(), ItemAdapter.Listener {

    private var itemList = listOf(
        Figure(R.drawable.triangle, "Triangle"),
        Figure(R.drawable.circle, "Circle"),
        Figure(R.drawable.square, "Square"),
        Figure(R.drawable.rectangle, "Rectangle"),
        SomeText("Sample text"),
        SomeText("Another one"),
        SomeText("Last one")
    )

    private val adapter = ItemAdapter(this)
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentRecyclerViewBinding.inflate(inflater)
        init()
        return binding.root
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@RecyclerViewFragment.context, 3)
            rcView.adapter = adapter
            val itemCount = (1..15).random()
            adapter.addItem(itemList, itemCount)
            bItemsCount.setOnClickListener {
                showItemsCount(itemCount)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecyclerViewFragment()
    }

    override fun onClick(figure: Figure) {
        Toast.makeText(this@RecyclerViewFragment.context, figure.toastText, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(someText: SomeText) {
        Toast.makeText(this@RecyclerViewFragment.context, someText.someText, Toast.LENGTH_SHORT)
            .show()
    }

    private fun showItemsCount(itemCount: Int) {

        val itemsCountNotification =
            this@RecyclerViewFragment.context?.let { NotificationCompat
                .Builder(it, ApplicationClass.CHANNEL_ID_RW)
                .setSmallIcon(R.drawable.ic_pause)
                .setContentTitle("WOW!")
                .setContentText("There is ${itemCount+1} items on screen")
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)}

        this.context?.let {
            if (itemsCountNotification != null) {
                NotificationManagerCompat.from(it).notify(33, itemsCountNotification.build())
            }
        }
    }
}