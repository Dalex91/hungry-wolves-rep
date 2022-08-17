package com.example.hungrywolves.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hungrywolves.databinding.FragmentFavoritesScreenBinding

class FavoritesScreenFragment : Fragment() {
    private lateinit var binding : FragmentFavoritesScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesScreenBinding.inflate(inflater)
        return binding.root
    }
}