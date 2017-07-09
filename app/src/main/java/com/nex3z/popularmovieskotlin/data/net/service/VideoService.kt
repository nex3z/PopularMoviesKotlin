package com.nex3z.popularmovieskotlin.data.net.service

import com.nex3z.popularmovieskotlin.data.entity.video.GetVideosResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService {

    @GET("/3/movie/{movieId}/videos")
    fun getVideos(@Path("movieId") movieId: Long): Single<GetVideosResponse>

}