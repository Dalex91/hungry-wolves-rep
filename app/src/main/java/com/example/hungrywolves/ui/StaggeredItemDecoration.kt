package com.example.hungrywolves.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class StaggeredItemDecoration : RecyclerView.ItemDecoration(){

    companion object DecorationDim {
        const val LEFT = 50
        const val RIGHT = 50
        const val TOP = 50
        const val BOTTOM = 50
        const val TOP_FIRST = 250
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            left = LEFT
            right = RIGHT
            top = TOP
            bottom = BOTTOM
            if(parent.getChildAdapterPosition(view) == 1)
                top = TOP_FIRST
        }
    }
}