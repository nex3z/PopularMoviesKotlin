package com.nex3z.popularmoviekotlin.util

import android.content.Context
import android.util.TypedValue
import com.nex3z.popularmoviekotlin.app.App

object ViewUtil {

    fun dpToPx(dp: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, App.appContext.resources.displayMetrics)
    }

}