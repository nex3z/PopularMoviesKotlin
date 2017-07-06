package com.nex3z.popularmovieskotlin.data.repository

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import io.reactivex.Observable
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.net.service.MovieService

class MovieRepositoryImpl(restClient: RestClient) : MovieRepository {

    val movieService: MovieService = restClient.getMovieService()

    override fun discoverMovies(page: Int, sortBy: String): Observable<DiscoverMovieResponse> {
        return movieService.discoverMovies(page, sortBy)
    }

}
