package com.nex3z.popularmovieskotlin.data.repository.video

import com.nex3z.popularmovieskotlin.data.entity.video.GetVideosResponse
import io.reactivex.Single

interface VideoRepository {

    fun getVideos(movieId: Long): Single<GetVideosResponse>

}