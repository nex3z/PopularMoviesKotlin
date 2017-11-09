package com.nex3z.popularmoviekotlin.data.net.service

import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {

    @GET("/3/movie/{movieId}/videos")
    fun getVideos(@Path("movieId") movieId: Long): Single<GetMovieVideosResponse>

}