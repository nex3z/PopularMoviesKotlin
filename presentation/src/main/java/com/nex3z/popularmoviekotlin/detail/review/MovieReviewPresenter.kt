package com.nex3z.popularmoviekotlin.detail.review

import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.GetReviewUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel

class MovieReviewPresenter(val movie: MovieModel) : BasePresenter<MovieReviewView>() {

    private val getReviewUseCase: GetReviewUseCase = App.service.create(::GetReviewUseCase)

    override fun destroy() {
        getReviewUseCase.dispose()
        super.destroy()
    }

    fun init() {
        fetchReviews()
    }

    fun fetchReviews() {
        getReviewUseCase.execute(ReviewObserver(), GetReviewUseCase.Params.forMovie(movie.id))
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