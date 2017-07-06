package com.nex3z.popularmovieskotlin.presentation.ui.misc

import android.content.Context
import android.util.TypedValue

object ViewUtil {

    fun dpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
    }

}