package com.example.treneeproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.treneeproject.databinding.FigureItemBinding

class FigureAdapter(private val listener: Listener): RecyclerView.Adapter<FigureAdapter.FigureHolder>() {

    private val figures = ArrayList<Figure>()

    class FigureHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = FigureItemBinding.bind(item)
        fun bind(figure: Figure, listener: Listener){
            binding.imageView.setImageResource(figure.imageId)
            itemView.setOnClickListener{
                listener.onClick(figure)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FigureHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.figure_item, parent, false)
        return FigureHolder(view)

    }

    override fun onBindViewHolder(holder: FigureHolder, position: Int) {
        holder.bind(figures[position], listener)
    }

    override fun getItemCount(): Int {
        return figures.size
    }

    fun addFigure(list: List<Figure>){
        figures.clear()
        for (i in 0..9) figures.add(list[(0..3).random()])

    }

    interface Listener{
        fun onClick(figure: Figure)
    }

}