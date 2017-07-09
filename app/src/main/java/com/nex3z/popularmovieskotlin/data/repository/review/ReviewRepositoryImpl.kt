package com.nex3z.popularmovieskotlin.data.repository.review

import com.nex3z.popularmovieskotlin.data.entity.review.GetReviewsResponse
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.net.service.ReviewService
import io.reactivex.Single

class ReviewRepositoryImpl(restClient: RestClient) : ReviewRepository {

    private val reviewService: ReviewService = restClient.reviewService

    override fun getReviews(movieId: Long): Single<GetReviewsResponse> {
        return reviewService.getReviews(movieId)
    }

}