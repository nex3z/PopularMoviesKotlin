package com.nex3z.popularmoviekotlin.data.net.service

import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewService {

    @GET("/3/movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: Long): Single<GetMovieReviewsResponse>

}