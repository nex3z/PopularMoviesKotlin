package com.nex3z.popularmovieskotlin.domain.interactor.review

import com.nex3z.popularmovieskotlin.data.repository.review.ReviewRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModelMapper
import io.reactivex.Observable

class GetReviewsUseCase(
        private val reviewRepository: ReviewRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<List<ReviewModel>, GetReviewsUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<ReviewModel>> {
        return reviewRepository.getReviews(params.movieId)
                .map(ReviewModelMapper::transform)
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