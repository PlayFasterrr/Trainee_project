package com.example.treneeproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.treneeproject.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentBlankBinding.inflate(inflater)
        return binding.root


    }

    companion object {

        @JvmStatic
        fun newInstance() = BlankFragment()

    }
}