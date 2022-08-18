package com.example.hungrywolves.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val MARGIN = 32
class StaggeredItemDecoration : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            top = MARGIN
            if(parent.getChildAdapterPosition(view) == 0)
                top = 0
            if(parent.getChildAdapterPosition(view) % 2 == 0) {
                left = 32
                right = 32
            }
            else {
                left = 0
                right = 32
            }

            if(parent.getChildAdapterPosition(view) == 1)
                top = 250
        }
    }
}