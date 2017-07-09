package com.nex3z.popularmovieskotlin.presentation.ui.detail.review

import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.review.GetReviewsUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BasePresenter

class ReviewPresenter(
        private val getReviewsUseCase: GetReviewsUseCase
) : BasePresenter<ReviewView>() {

    private lateinit var movie: MovieModel
    private var reviews: List<ReviewModel> = emptyList()

    override fun destroy() {
        super.destroy()
        getReviewsUseCase.dispose()
    }

    fun init(movie: MovieModel) {
        this.movie = movie
        fetchReview();
    }

    private fun fetchReview() {
        getReviewsUseCase.execute(GetReviewsObserver(),
                GetReviewsUseCase.Params.forMovie(movie.id))
    }

    private inner class GetReviewsObserver : DefaultObserver<List<ReviewModel>>() {
        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<ReviewModel>) {
            super.onNext(data)
            reviews = data
            view?.hideLoading()
            view?.renderReviews(reviews)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message ?: "")
            throwable.printStackTrace()
        }
    }

    companion object {
        private val LOG_TAG = "ReviewPresenter"
    }
}