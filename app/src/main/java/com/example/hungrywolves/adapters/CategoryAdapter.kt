package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.R
import com.example.hungrywolves.databinding.HorizontalCategoryItemsBinding
import com.example.hungrywolves.network.Category

class CategoryAdapter(val actionOnButton: (Category) -> Unit)
    : ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(DiffCallback){

    class CategoryViewHolder(
        private var binding : HorizontalCategoryItemsBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val itemUnderBar = binding.itemBar
        val itemButton = binding.buttonCategory

        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category) =
            oldItem.idCategory == newItem.idCategory

        override fun areContentsTheSame(oldItem: Category, newItem: Category) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            HorizontalCategoryItemsBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        val context = holder.itemButton.context
        holder.itemButton.setOnClickListener{
            actionOnButton(category)
        }
        holder.itemButton.setTextColor(context.getColor(if (category.selected)
            R.color.vermilion else R.color.black))
        holder.itemUnderBar.visibility = if(category.selected) View.VISIBLE else View.INVISIBLE
        holder.bind(category)
    }
}