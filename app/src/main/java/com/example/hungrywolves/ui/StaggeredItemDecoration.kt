package com.example.hungrywolves.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class StaggeredItemDecoration : RecyclerView.ItemDecoration(){

    companion object DecorationDim {
        const val MARGINS = 60
        const val TOP_FIRST = 250
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = MARGINS
            right = MARGINS
            top = MARGINS
            bottom = MARGINS
            if(parent.getChildAdapterPosition(view) == 1)
                top = TOP_FIRST
        }
    }
}