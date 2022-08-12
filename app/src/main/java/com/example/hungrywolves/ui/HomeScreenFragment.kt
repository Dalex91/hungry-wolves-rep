package com.example.hungrywolves.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.hungrywolves.R
import com.example.hungrywolves.adapters.CategoryAdapter
import com.example.hungrywolves.adapters.MealAdapter
import com.example.hungrywolves.databinding.FragmentHomeScreenBinding
import com.example.hungrywolves.model.HomeScreenViewModel

class HomeScreenFragment : Fragment(R.layout.fragment_home_screen) {
    private val viewModelHomeScreen : HomeScreenViewModel by viewModels()
    private val adapterCategory : CategoryAdapter by lazy {
        CategoryAdapter(viewModelHomeScreen::filterCategories)
    }
    private val mealAdapter : MealAdapter by lazy {
        MealAdapter(this::goToDetailScreen)
    }
    private lateinit var binding : FragmentHomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@HomeScreenFragment
            viewModel = viewModelHomeScreen
            horizontalRecycleViewCategories.adapter = adapterCategory
            horizontalRecycleViewMeals.adapter = mealAdapter
        }
        viewModelHomeScreen.apply {
            categories.observe(viewLifecycleOwner, adapterCategory::submitList)
            meals.observe(viewLifecycleOwner, mealAdapter::submitList)
        }
        binding.searchButton.setOnClickListener {
            goToSearchScreen()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
        context?.let {
            ContextCompat.getDrawable(
                it,
                R.drawable.horizontal_space_recycle_view)?.let { itemDecoration.setDrawable(it) }
        }
        binding.horizontalRecycleViewMeals.addItemDecoration(itemDecoration)
    }

    private fun goToSearchScreen() {
        findNavController().navigate(R.id.action_home_screen_fragment_to_search_screen_fragment)
    }

    private fun goToDetailScreen() {
        findNavController().navigate(R.id.action_home_screen_fragment_to_detail_screen_fragment)
    }
}