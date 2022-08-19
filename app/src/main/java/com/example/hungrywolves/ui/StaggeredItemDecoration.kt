package com.example.hungrywolves.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

const val MARGIN = 32
const val MARGIN_TOP = 150
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
                left = MARGIN
                right = MARGIN
            }
            else {
                left = 0
                right = MARGIN
            }

            if(parent.getChildAdapterPosition(view) == 1)
                top = MARGIN_TOP
        }
    }
}