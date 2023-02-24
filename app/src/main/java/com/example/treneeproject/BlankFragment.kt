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
        Figure(R.drawable.triangle),
        Figure(R.drawable.circle),
        Figure(R.drawable.square),
        Figure(R.drawable.rectangle),
        Figure(R.drawable.triangle),
        Figure(R.drawable.circle),
        Figure(R.drawable.square),
        Figure(R.drawable.rectangle),
        Figure(R.drawable.circle),
        Figure(R.drawable.triangle)

    )

    private val adapter = FigureAdapter()
    lateinit var binding: FragmentBlankBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBlankBinding.inflate(inflater)
        init()
        return binding.root

    }


    private fun init(){
        binding.apply {
            rcView.layoutManager = GridLayoutManager(this@BlankFragment.context, 3)
            rcView.adapter = adapter
            adapter.addFigure(imageIdList)

        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment()
    }

}