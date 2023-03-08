package com.example.treneeproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.treneeproject.databinding.FigureItemBinding
import com.example.treneeproject.databinding.TextItemBinding
import com.example.treneeproject.recyclerview.dataclsses.Figure
import com.example.treneeproject.recyclerview.dataclsses.SomeText

class ItemAdapter(private val listener: Listener) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        private const val FIGURE_TYPE = 1
        private const val TEXT_TYPE = 2
    }

    private val itemsToAdd = ArrayList<Any>()


    class TextHolder(itemView: View) : ViewHolder(itemView) {
        private val binding = TextItemBinding.bind(itemView)
        fun bind(someText: SomeText, listener: Listener) {
            binding.textView.text = someText.someText
            itemView.setOnClickListener {
                listener.onClick(someText)
            }
        }

    }

    class FigureHolder(item: View) : ViewHolder(item) {
        private val binding = FigureItemBinding.bind(item)
        fun bind(figure: Figure, listener: Listener) {
            binding.imageView.setImageResource(figure.imageId)
            itemView.setOnClickListener {
                listener.onClick(figure)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemsToAdd[position]) {
            is Figure -> FIGURE_TYPE
            is SomeText -> TEXT_TYPE
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            FIGURE_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.figure_item, parent, false)
                FigureHolder(view)
            }
            TEXT_TYPE -> {
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.text_item, parent, false)
                TextHolder(view)
            }
            else -> throw IllegalArgumentException("OOOOOPS!")
        }
    }

    override fun getItemCount(): Int {
        return itemsToAdd.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (holder) {
            is FigureHolder -> {
                holder.bind(figure = itemsToAdd[position] as Figure, listener)
            }
            is TextHolder -> {

                holder.bind(someText = itemsToAdd[position] as SomeText, listener)
            }
            else -> throw IllegalArgumentException("OOOOOPS!")
        }
    }

    fun addItem(list: List<Any>, numberOfItems:Int) {
        itemsToAdd.clear()

        for (i in 0..numberOfItems) itemsToAdd.add(list[(list.indices).random()])
    }

    interface Listener {
        fun onClick(figure: Figure)
        fun onClick(someText: SomeText)
    }
}