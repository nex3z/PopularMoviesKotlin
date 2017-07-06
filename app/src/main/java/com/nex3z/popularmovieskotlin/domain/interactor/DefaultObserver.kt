package com.nex3z.popularmovieskotlin.domain.interactor

import io.reactivex.observers.DisposableObserver

open class DefaultObserver<T> : DisposableObserver<T>() {

    override fun onNext(data: T) {}

    override fun onComplete() {}

    override fun onError(throwable: Throwable) {}

}
