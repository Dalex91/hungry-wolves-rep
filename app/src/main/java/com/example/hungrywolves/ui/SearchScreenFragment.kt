package com.example.hungrywolves.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolves.R
import com.example.hungrywolves.adapters.StaggeredMealAdapter
import com.example.hungrywolves.databinding.FragmentSearchScreenBinding
import com.example.hungrywolves.model.SearchScreenViewModel

class SearchScreenFragment : Fragment() {
    private val viewModelSearchScreen : SearchScreenViewModel by viewModels()
    private lateinit var binding : FragmentSearchScreenBinding
    private val mealAdapter : StaggeredMealAdapter by lazy {
        StaggeredMealAdapter(this::goToDetailScreen)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScreenBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@SearchScreenFragment
            viewModel = viewModelSearchScreen
            staggeredRecycleView.adapter = mealAdapter
            staggeredRecycleView.addItemDecoration(StaggeredItemDecoration())
            imageBackButton.setOnClickListener {
                viewModelSearchScreen.cleanText()
                findNavController().navigateUp()
            }
        }
        viewModelSearchScreen.apply {
            meals.observe(viewLifecycleOwner, mealAdapter::submitList)
            mealName.observe(viewLifecycleOwner) {
                getSearchedMeals()
            }
        }
        return binding.root
    }

    private fun goToDetailScreen() {
        findNavController().navigate(R.id.action_search_screen_fragment_to_detail_screen_fragment)
    }
}
