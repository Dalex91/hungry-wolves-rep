package com.example.hungrywolves.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolves.R
import com.example.hungrywolves.adapters.FavouriteAdapter
import com.example.hungrywolves.databinding.FragmentFavoritesScreenBinding
import com.example.hungrywolves.model.FavouritesScreenViewModel

class FavoritesScreenFragment : Fragment() {
    private val viewModelFavScreen : FavouritesScreenViewModel by viewModels()
    private lateinit var binding : FragmentFavoritesScreenBinding
    private val favAdapter by lazy {
        FavouriteAdapter(this::goToDetailScreen, this::showPopUp)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesScreenBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = this@FavoritesScreenFragment
            favouritesReycleView.adapter = favAdapter
            viewModel = viewModelFavScreen
        }
        viewModelFavScreen.apply {
            viewModelFavScreen.getFavourites()
            favourites.observe(viewLifecycleOwner, favAdapter::submitList)
        }
        return binding.root
    }

    private fun goToDetailScreen(id : String) {
        val action = FavoritesScreenFragmentDirections.actionFavouritesScreenFragmentToDetailScreenFragment(id)
        findNavController().navigate(action)
    }

    private fun showPopUp(id : String, holder : FavouriteAdapter.FavouriteViewHolder) {
        PopupMenu(context, holder.mealCard).apply {
            menuInflater.inflate(R.menu.popup_menu, menu)
            setOnMenuItemClickListener {
                viewModelFavScreen.deleteItem(id)
                true
            }
            show()
        }
    }
}