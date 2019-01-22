package com.nex3z.popularmovieskotlin.app.misc

import android.util.TypedValue
import com.nex3z.popularmovieskotlin.app.App

fun dpToPx(dp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, dp, App.appContext.resources.displayMetrics)
