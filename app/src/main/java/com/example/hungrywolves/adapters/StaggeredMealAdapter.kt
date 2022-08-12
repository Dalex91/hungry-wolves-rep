package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.databinding.StaggeredItemBinding
import com.example.hungrywolves.network.data_model.Meal

class StaggeredMealAdapter(val actionPerform: () -> (Unit)) : ListAdapter<Meal, StaggeredMealAdapter.StaggeredMealViewHolder>(MealAdapter.DiffCallback){

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
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener{
            actionPerform()
        }
    }

}
