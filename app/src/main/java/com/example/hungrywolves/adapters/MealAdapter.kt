package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.databinding.HorizontalItemBinding
import com.example.hungrywolves.network.data_model.Meal

class MealAdapter : ListAdapter<Meal, MealAdapter.MealViewHolder>(DiffCallback){

    class MealViewHolder(
        private var binding : HorizontalItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal) {
            binding.meal = meal
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal) =
            oldItem.idMeal == newItem.idMeal

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MealViewHolder(
            HorizontalItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
