package com.example.treneeproject

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.treneeproject.databinding.FragmentBlankBinding

class BlankFragment : Fragment(), ItemAdapter.Listener{

    private val itemList = listOf(
        Figure(R.drawable.triangle, "Triangle"),
        Figure(R.drawable.circle, "Circle"),
        Figure(R.drawable.square, "Square"),
        Figure(R.drawable.rectangle, "Rectangle"),
        SomeText("Sample text"),
        SomeText("Another one"),
        SomeText("Last one")
    )

    private val adapter = ItemAdapter(this)
    private lateinit var binding: FragmentBlankBinding

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
            adapter.addItem(itemList)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance() = BlankFragment()
    }

    override fun onClick(figure: Figure) {
        Toast.makeText(this@BlankFragment.context, figure.toastText, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(someText: SomeText) {
        Toast.makeText(this@BlankFragment.context, someText.someText, Toast.LENGTH_SHORT).show()
    }
}