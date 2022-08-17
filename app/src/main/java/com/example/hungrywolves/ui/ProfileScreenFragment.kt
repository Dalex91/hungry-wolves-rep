package com.example.hungrywolves.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.hungrywolves.R
import com.example.hungrywolves.databinding.FragmentProfileScreenBinding
import com.example.hungrywolves.model.ProfileScreenViewModel

class ProfileScreenFragment : Fragment() {
    private lateinit var binding : FragmentProfileScreenBinding
    private val viewModelProfileScreen : ProfileScreenViewModel  by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileScreenBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@ProfileScreenFragment
            viewModel = viewModelProfileScreen
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            termsAndConditionPage.setOnClickListener {
                goToTermsAndConditionScreen()
            }
            termsAndConditionButton.setOnClickListener {
                goToTermsAndConditionScreen()
            }
        }
        viewModelProfileScreen.getProfileUser()
        return binding.root
    }

    private fun goToTermsAndConditionScreen() {
        findNavController().navigate(R.id.action_profile_screen_fragment_to_terms_and_condition_fragment)
    }
}