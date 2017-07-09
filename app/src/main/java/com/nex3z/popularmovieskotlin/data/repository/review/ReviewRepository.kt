package com.nex3z.popularmovieskotlin.data.repository.review

import com.nex3z.popularmovieskotlin.data.entity.review.GetReviewsResponse
import io.reactivex.Single

interface ReviewRepository {

    fun getReviews(movieId: Long): Single<GetReviewsResponse>

}