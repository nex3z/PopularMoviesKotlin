package com.nex3z.popularmovieskotlin.app.detail.review

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nex3z.popularmovieskotlin.domain.PopMovieService
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.review.GetReviewUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel

class MovieReviewViewModel(
    application: Application,
    service: PopMovieService
) : AndroidViewModel(application) {
    private val getReviewUseCase: GetReviewUseCase = service.create(::GetReviewUseCase)
    private val _reviews = MutableLiveData<List<ReviewModel>>()

    lateinit var movie: MovieModel

    val reviews: LiveData<List<ReviewModel>> = _reviews

    fun fetchVideos() {
        getReviewUseCase.execute(ReviewObserver(), GetReviewUseCase.Params.forMovie(movie.id))
    }

    private inner class ReviewObserver : DefaultObserver<List<ReviewModel>>() {
        override fun onNext(data: List<ReviewModel>) {
            _reviews.value = data
        }
    }

}