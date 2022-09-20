package com.example.hungrywolves.ui.termsAndCondition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.fragment.app.Fragment
import com.example.hungrywolves.databinding.FragmentTermsAndConditionsBinding

const val URL = "https://www.wolfpack-digital.com/privacy"

class TermsAndConditionsFragment : Fragment() {
    private lateinit var binding : FragmentTermsAndConditionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTermsAndConditionsBinding.inflate(inflater)
        val webPage : WebView = binding.webTermsAndCondition
        webPage.apply {
            loadUrl(URL)
            settings.apply {
                javaScriptEnabled = true
                allowContentAccess = true
                domStorageEnabled = true
                useWideViewPort = true
            }
        }
        return binding.root
    }
}