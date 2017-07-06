package com.nex3z.popularmovieskotlin.data.entity.movie

data class MovieEntity(
        val poster_path: String = "",
        val adult: Boolean = false,
        val overview: String = "",
        val release_date: String = "",
        val genre_ids: List<Int>? = null,
        val id: String = "",
        val original_title: String = "",
        val original_language: String = "",
        val title: String = "",
        val backdrop_path: String = "",
        val popularity: Double = 0.0,
        val vote_count: Int = 0,
        val video: Boolean = false,
        val vote_average: Double = 0.0
)
