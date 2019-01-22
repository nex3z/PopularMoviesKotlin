package com.nex3z.popularmovieskotlin.domain.interactor.review

import com.nex3z.popularmovieskotlin.data.entity.review.GetMovieReviewsResp
import com.nex3z.popularmovieskotlin.domain.Context
import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import com.nex3z.popularmovieskotlin.domain.model.review.transform
import io.reactivex.Observable

class GetReviewUseCase(context: Context)
    : BaseUseCase<List<ReviewModel>, GetReviewUseCase.Params>(context) {

    override fun buildUseCaseObservable(params: Params): Observable<List<ReviewModel>> {
        return context.movieRepository
            .getReviews(params.movieId)
            .map(GetMovieReviewsResp::transform)
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
