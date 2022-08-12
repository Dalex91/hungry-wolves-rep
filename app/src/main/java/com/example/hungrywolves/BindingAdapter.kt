package com.example.hungrywolves

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.transition.Visibility
import coil.load

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("textVisibility")
fun bindTextView(textView: TextView, visibility: Boolean) {
    if(visibility)
        textView.visibility = TextView.VISIBLE
    else
        textView.visibility = TextView.INVISIBLE
}

