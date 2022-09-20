package com.example.hungrywolves

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.databinding.BindingAdapter
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

@BindingAdapter(value = ["android:ingredient", "android:measure"])
fun bindCustomText(textView: TextView, ingredient : String?, measure : String?) {
    textView.text = buildSpannedString {
        measure?.let {
            append("$it ")
        }
        bold {
            ingredient?.let {
                append(it)
            }
        }
    }
}

@BindingAdapter("custom_visibility")
fun bindPlaceHolderVisibility(view: View, visibility: Boolean) {
    view.visibility = if (visibility)
        View.VISIBLE
    else
        View.GONE
}




