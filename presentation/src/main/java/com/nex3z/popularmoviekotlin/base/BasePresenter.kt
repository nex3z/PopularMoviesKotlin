package com.nex3z.popularmoviekotlin.base

abstract class BasePresenter<View: BaseView> : Presenter {

    var view: View? = null;

    override fun resume() {}

    override fun pause() {}

    override fun destroy() {
        view = null
    }

}