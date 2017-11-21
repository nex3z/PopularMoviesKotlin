package com.nex3z.popularmoviekotlin.domain.model.video

data class VideoModel(
        val id: String = "",
        val iso6391: String = "",
        val iso31661: String = "",
        val key: String = "",
        val name: String = "",
        val site: String = "",
        val size: Int = 0,
        val type: String = ""
) {

    fun getVideoUrl(): String {
        return YOUTUBE_BASE_URL + key
    }

    companion object {
        private val YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v="
    }

}
