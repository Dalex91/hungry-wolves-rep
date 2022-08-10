package com.example.hungrywolves.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.hungrywolves.MainActivity
import com.example.hungrywolves.R
import com.example.hungrywolves.adapters.MealAdapter
import com.example.hungrywolves.adapters.StaggeredMealAdapter
import com.example.hungrywolves.databinding.FragmentSearchScreenBinding
import com.example.hungrywolves.model.SearchScreenViewModel

class SearchScreenFragment : Fragment() {
    private val viewModelSearchScreen : SearchScreenViewModel by viewModels()
    private lateinit var binding : FragmentSearchScreenBinding
    private val mealAdapter : StaggeredMealAdapter by lazy {
        StaggeredMealAdapter()
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
            }
        }
        viewModelSearchScreen.apply {
            meals.observe(viewLifecycleOwner, mealAdapter::submitList)
            mealName.observe(viewLifecycleOwner) {
                getSearchedMeals()
            }
        }
        activity?.findViewById<View>(R.id.menu_navigation)?.visibility = View.INVISIBLE
        return binding.root
    }
}
