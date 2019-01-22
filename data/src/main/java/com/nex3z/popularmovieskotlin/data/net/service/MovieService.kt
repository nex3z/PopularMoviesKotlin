package com.nex3z.popularmovieskotlin.data.net.service

import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieResp
import com.nex3z.popularmovieskotlin.data.entity.review.GetMovieReviewsResp
import com.nex3z.popularmovieskotlin.data.entity.video.GetMovieVideosResp
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieService {

    @GET("/3/discover/movie")
    fun discoverMovies(@QueryMap params: Map<String, String>): Single<DiscoverMovieResp>

    @GET("/3/movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: Long): Single<GetMovieReviewsResp>

    @GET("/3/movie/{movieId}/videos")
    fun getVideos(@Path("movieId") movieId: Long): Single<GetMovieVideosResp>

}