package com.nex3z.popularmovieskotlin.data.repository

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import io.reactivex.Observable

interface MovieRepository {

    fun discoverMovies(page: Int, sortBy: String): Observable<DiscoverMovieResponse>

}