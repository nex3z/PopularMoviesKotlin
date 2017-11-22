package com.nex3z.popularmoviekotlin.detail.review

import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.GetReviewsUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel

class MovieReviewPresenter(val movie: MovieModel) : BasePresenter<MovieReviewView>() {

    private val getReviewsUseCase: GetReviewsUseCase = App.service.create(GetReviewsUseCase::class)

    override fun destroy() {
        getReviewsUseCase.dispose()
        super.destroy()
    }

    fun init() {
        fetchReviews()
    }

    fun fetchReviews() {
        getReviewsUseCase.execute(ReviewObserver(), GetReviewsUseCase.Params.forMovie(movie.id))
    }

    private inner class ReviewObserver : DefaultObserver<List<ReviewModel>>() {
        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<ReviewModel>) {
            super.onNext(data)
            view?.hideLoading()
            view?.renderReviews(data)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message)
        }
    }
}