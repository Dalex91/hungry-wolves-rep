package com.example.hungrywolves.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.hungrywolves.databinding.FragmentNoInternetScreenBinding


class NoInternetScreenFragment : Fragment() {
    private lateinit var binding: FragmentNoInternetScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoInternetScreenBinding.inflate(inflater, container, false)
        binding.lifecycleOwner  = this@NoInternetScreenFragment
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {}
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        return binding.root
    }

}