package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.databinding.HorizontalItemBinding
import com.example.hungrywolves.models.Meal

class MealAdapter(val actionPerform : (String) -> Unit) : ListAdapter<Meal, MealAdapter.MealViewHolder>(DiffCallback){

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
        val meal = getItem(position)
        holder.bind(meal)
        holder.itemView.setOnClickListener {
            actionPerform(meal.idMeal.toString())
        }
    }
}
