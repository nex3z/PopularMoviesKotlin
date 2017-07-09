package com.nex3z.popularmovieskotlin.data.entity.video

data class GetVideosResponse(
        val id: Long = 0,
        val results: List<VideoEntity> = emptyList()
)
