package com.nex3z.popularmovieskotlin.data.net.service

import com.nex3z.popularmovieskotlin.data.entity.review.GetReviewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {

    @GET("/3/movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: Long): Single<GetReviewsResponse>

}