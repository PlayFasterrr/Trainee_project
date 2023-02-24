package com.example.treneeproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treneeproject.databinding.FigureItemBinding

class FigureAdapter: RecyclerView.Adapter<FigureAdapter.FigureHolder>() {

    private val figures = ArrayList<Figure>()

    class FigureHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = FigureItemBinding.bind(item)
        fun bind(figure: Figure){
            binding.imageView.setImageResource(figure.imageId)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.figure_item, parent, false)
        return FigureHolder(view)

    }

    override fun onBindViewHolder(holder: FigureHolder, position: Int) {
        holder.bind(figures[position])
    }

    override fun getItemCount(): Int {
        return figures.size
    }


    fun addFigure(list: List<Figure>) {
        figures.clear()
        figures.addAll(list)
        notifyDataSetChanged()

    }


}