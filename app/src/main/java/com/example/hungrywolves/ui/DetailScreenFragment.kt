package com.example.hungrywolves.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.hungrywolves.R
import com.example.hungrywolves.adapters.TagAdapter
import com.example.hungrywolves.databinding.FragmentDetailScreenBinding
import com.example.hungrywolves.model.DetailScreenViewModel

class DetailScreenFragment : Fragment() {

    private lateinit var binding : FragmentDetailScreenBinding
    private val viewModelDetailScreen : DetailScreenViewModel by viewModels()
    private val args : DetailScreenFragmentArgs by navArgs()
    private val tagAdapter : TagAdapter by lazy {
        TagAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailScreenBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@DetailScreenFragment
            viewModel = viewModelDetailScreen
            backButton.setOnClickListener {
                findNavController().navigateUp()
            }
            horizontalRecycleViewTags.adapter = tagAdapter
            favouriteButton.apply {
                setOnCheckedChangeListener { _, isChecked ->
                    background = ContextCompat.getDrawable(requireContext(),
                        if(isChecked) R.drawable.ic_favourite_pressed else
                            R.drawable.ic_favourite)
                }
            }
        }
        viewModelDetailScreen.apply {
            getMealDetails(args.idMeal)
            tags.observe(viewLifecycleOwner, tagAdapter::submitList)
        }
        return binding.root
    }

}