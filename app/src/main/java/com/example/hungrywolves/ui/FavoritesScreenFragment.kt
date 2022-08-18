package com.example.hungrywolves.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hungrywolves.adapters.FavouriteAdapter
import com.example.hungrywolves.databinding.FragmentFavoritesScreenBinding
import com.example.hungrywolves.model.FavouritesScreenViewModel

class FavoritesScreenFragment : Fragment() {
    private val viewModelFavScreen : FavouritesScreenViewModel by viewModels()
    private lateinit var binding : FragmentFavoritesScreenBinding
    private val favAdapter by lazy {
        FavouriteAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesScreenBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@FavoritesScreenFragment
            favouritesReycleView.adapter = favAdapter
            viewModel = viewModelFavScreen
        }
        return binding.root
    }
}