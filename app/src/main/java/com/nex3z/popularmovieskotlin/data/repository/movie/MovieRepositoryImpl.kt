package com.nex3z.popularmovieskotlin.data.repository.movie

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.net.service.MovieService
import io.reactivex.Single

class MovieRepositoryImpl(restClient: RestClient) : MovieRepository {

    private val movieService: MovieService = restClient.movieService

    override fun discoverMovies(page: Int, sortBy: String): Single<DiscoverMovieResponse> {
        return movieService.discoverMovies(page, sortBy)
    }

}
