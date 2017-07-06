package com.nex3z.popularmovieskotlin.presentation.ui.misc

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class SpacesItemDecoration : RecyclerView.ItemDecoration {
    private var spaceTop: Int = 0
    private var spaceRight: Int = 0
    private var spaceBottom: Int = 0
    private var spaceLeft: Int = 0

    constructor(space: Int) {
        spaceLeft = space
        spaceTop = space
        spaceRight = space
        spaceBottom = space
    }

    constructor(left: Int, top: Int, right: Int, bottom: Int) {
        spaceLeft = left
        spaceTop = top
        spaceRight = right
        spaceBottom = bottom
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        outRect.left = spaceLeft
        outRect.top = spaceTop
        outRect.right = spaceRight
        outRect.bottom = spaceBottom
    }
}