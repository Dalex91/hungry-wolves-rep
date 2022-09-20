package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.databinding.StaggeredItemBinding
import com.example.hungrywolves.models.Meal

class StaggeredMealAdapter(val actionPerform: (String) -> (Unit)) : ListAdapter<Meal, StaggeredMealAdapter.StaggeredMealViewHolder>(MealAdapter.DiffCallback){

    class StaggeredMealViewHolder(
        private var binding : StaggeredItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(meal: Meal) {
            binding.meal = meal
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StaggeredMealViewHolder(
            StaggeredItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    override fun onBindViewHolder(holder: StaggeredMealViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)
        holder.itemView.setOnClickListener{
            actionPerform(meal.idMeal.toString())
        }
    }

}
