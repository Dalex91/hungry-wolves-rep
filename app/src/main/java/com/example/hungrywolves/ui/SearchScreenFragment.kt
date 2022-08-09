package com.example.hungrywolves.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hungrywolves.R
import com.example.hungrywolves.databinding.FragmentHomeScreenBinding
import com.example.hungrywolves.databinding.FragmentSearchScreenBinding
import com.example.hungrywolves.model.SearchScreenViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchScreenFragment : Fragment() {
    private val viewModelSearchScreen : SearchScreenViewModel by viewModels()
    private lateinit var binding : FragmentSearchScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@SearchScreenFragment
            viewModel = viewModelSearchScreen
        }
        activity?.findViewById<View>(R.id.menu_navigation)?.visibility = View.INVISIBLE
        return binding.root
    }
}