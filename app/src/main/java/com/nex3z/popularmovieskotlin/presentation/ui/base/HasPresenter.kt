package com.nex3z.popularmovieskotlin.presentation.ui.base

interface HasPresenter<out T: Presenter> {

    fun getPresenter(): T

}