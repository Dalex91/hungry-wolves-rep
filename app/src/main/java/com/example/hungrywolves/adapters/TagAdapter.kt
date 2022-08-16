package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.R
import com.example.hungrywolves.databinding.HorizontalCategoryItemsBinding
import com.example.hungrywolves.databinding.HorizontalTagItemBinding
import com.example.hungrywolves.network.data_model.Category
import com.example.hungrywolves.network.data_model.Meal

class TagAdapter
    : ListAdapter<String, TagAdapter.TagViewHolder>(DiffCallback){

    class TagViewHolder(
        private var binding : HorizontalTagItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val itemButton = binding.tagButton

        fun bind(tag: String) {
            binding.tag = tag
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder(
            HorizontalTagItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}