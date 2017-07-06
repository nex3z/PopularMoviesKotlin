package com.nex3z.popularmovieskotlin.data.entity.movie

data class DiscoverMovieResponse(
        val page: Int = 0,
        val results: List<MovieEntity>? = null,
        val total_results: Int = 0,
        val total_pages: Int = 0
)
