package com.nex3z.popularmovieskotlin.app.misc

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
    private val layoutManager: GridLayoutManager
) : RecyclerView.OnScrollListener() {

    var onLoadMore: (() -> Unit)? = null
    var loading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if(dy > 0) {
            val visibleItems = layoutManager.childCount;
            val total = layoutManager.itemCount;
            val pastItems = layoutManager.findFirstVisibleItemPosition();
            Log.v(LOG_TAG, "visibleItems = $visibleItems, total = $total, pastItems = $pastItems, loading = $loading")

            if (!loading) {
                if (visibleItems + pastItems >= total) {
                    loading = true;
                    Log.v(LOG_TAG, "fetching more");
                    onLoadMore?.invoke()
                }
            }
        }
    }

    companion object {
        private val LOG_TAG: String = EndlessScrollListener::class.java.simpleName
    }
}