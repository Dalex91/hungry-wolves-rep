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
    private val checked : Boolean = false

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
        }
        viewModelDetailScreen.apply {
            getMealDetails(args.idMeal)
            tags.observe(viewLifecycleOwner, tagAdapter::submitList)
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
        binding.horizontalRecycleViewTags.addItemDecoration(itemDecoration)
    }
}