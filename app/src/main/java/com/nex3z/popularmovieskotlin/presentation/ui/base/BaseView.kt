package com.nex3z.popularmovieskotlin.presentation.ui.base

import android.content.Context

interface BaseView {

    fun showMessage(message: String)

    fun showError(error: String)

    val viewContext: Context

}