package com.nex3z.popularmoviekotlin.util

import android.util.TypedValue
import com.nex3z.popularmoviekotlin.app.App

fun dpToPx(dp: Float): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, dp, App.appContext.resources.displayMetrics)
