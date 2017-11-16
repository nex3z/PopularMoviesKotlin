package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.domain.Context
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel
import com.nex3z.popularmoviekotlin.domain.model.review.transform
import io.reactivex.Observable

class GetReviewsUseCase(context: Context)
    : BaseUseCase<List<ReviewModel>, GetReviewsUseCase.Params>(context) {

    override fun buildUseCaseObservable(params: Params): Observable<List<ReviewModel>> {
        return context.movieRepository
                .getReviews(params.movieId)
                .map(::transform)
                .toObservable()
    }

    class Params private constructor(val movieId: Long) {
        companion object {
            fun forMovie(movieId: Long): Params {
                return Params(movieId)
            }
        }
    }
}
