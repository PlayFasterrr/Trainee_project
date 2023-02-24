package com.example.treneeproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.treneeproject.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    private val imageIdList = listOf(
        R.drawable.triangle,
        R.drawable.circle,
        R.drawable.square,
        R.drawable.rectangle,
        R.drawable.triangle,
        R.drawable.circle,
        R.drawable.square,
        R.drawable.rectangle,
        R.drawable.triangle,
        R.drawable.circle
    )

    private val adapter = FigureAdapter()
    lateinit var binding: FragmentBlankBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init()
        binding = FragmentBlankBinding.inflate(inflater)
        return binding.root

    }


    private fun init(){
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@BlankFragment.context, 3)
            rcView.adapter = adapter
            for (i in 0..9){
                val figure = Figure(imageIdList[i])
                adapter.addFigure(figure)
            }
            return

        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment()
    }

}