package com.example.hungrywolves.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hungrywolves.databinding.VerticalFavoriteItemBinding
import com.example.hungrywolves.network.data_model.MealDetail

class FavouriteAdapter(
    val actionPerform : (String) -> Unit,
    val deletePerform : (String, FavouriteViewHolder) -> Unit,
) : ListAdapter<MealDetail,
        FavouriteAdapter.FavouriteViewHolder>(DiffCallback){

    class FavouriteViewHolder(
        private var binding : VerticalFavoriteItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val mealCard = binding.mealCard
        fun bind(meal: MealDetail) {
            binding.meal = meal
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MealDetail>() {
        override fun areItemsTheSame(oldItem: MealDetail, newItem: MealDetail) =
            oldItem.idMeal == newItem.idMeal

        override fun areContentsTheSame(oldItem: MealDetail, newItem: MealDetail) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavouriteViewHolder(
            VerticalFavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)
        holder.itemView.setOnClickListener {
            actionPerform(meal.idMeal)
        }

        holder.itemView.setOnLongClickListener{
            deletePerform(meal.idMeal, holder)
            true
        }
    }
}