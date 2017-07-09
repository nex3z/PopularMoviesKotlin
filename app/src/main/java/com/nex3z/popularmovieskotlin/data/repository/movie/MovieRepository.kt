package com.nex3z.popularmovieskotlin.data.repository.movie

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import io.reactivex.Single

interface MovieRepository {

    fun discoverMovies(page: Int, sortBy: String): Single<DiscoverMovieResponse>

}