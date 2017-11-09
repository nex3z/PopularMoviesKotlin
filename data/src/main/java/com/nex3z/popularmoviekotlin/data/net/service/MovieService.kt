package com.nex3z.popularmoviekotlin.data.net.service

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieService {

    @GET("/3/discover/movie")
    fun discoverMovies(@QueryMap params: Map<String, String>): Single<DiscoverMovieResponse>

    @GET("/3/movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: Long): Single<GetMovieReviewsResponse>

    @GET("/3/movie/{movieId}/videos")
    fun getVideos(@Path("movieId") movieId: Long): Single<GetMovieVideosResponse>

}