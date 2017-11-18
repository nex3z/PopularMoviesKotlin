package com.nex3z.popularmoviekotlin.base

interface HasPresenter<out T: Presenter> {

    fun getPresenter(): T

}