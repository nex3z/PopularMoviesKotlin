package com.nex3z.popularmoviekotlin.misc

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacingItemDecoration(
        private val mSpacingTop: Int,
        private val mSpacingRight: Int,
        private val mSpacingBottom: Int,
        private val mSpacingLeft: Int
) : RecyclerView.ItemDecoration() {

    constructor(space: Int) : this(space, space, space, space) {}

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State?) {
        outRect.top = mSpacingTop
        outRect.right = mSpacingRight
        outRect.bottom = mSpacingBottom
        outRect.left = mSpacingLeft
    }

}