package com.nex3z.popularmovieskotlin.data.repository.video

import com.nex3z.popularmovieskotlin.data.entity.video.GetVideosResponse
import com.nex3z.popularmovieskotlin.data.net.RestClient
import io.reactivex.Single

class VideoRepositoryImpl(restClient: RestClient) : VideoRepository {

    private val videoService = restClient.videoService

    override fun getVideos(movieId: Long): Single<GetVideosResponse> {
        return videoService.getVideos(movieId)
    }

}