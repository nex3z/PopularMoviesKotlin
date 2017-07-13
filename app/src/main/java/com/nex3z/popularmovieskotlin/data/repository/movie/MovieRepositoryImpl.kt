package com.nex3z.popularmovieskotlin.data.repository.movie

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import com.nex3z.popularmovieskotlin.data.entity.movie.MovieEntity
import com.nex3z.popularmovieskotlin.data.local.LocalMovieDataStore
import com.nex3z.popularmovieskotlin.data.local.LocalMovieDataStoreImpl
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.net.service.MovieService
import io.reactivex.Single

class MovieRepositoryImpl(restClient: RestClient) : MovieRepository {

    private val movieService: MovieService = restClient.movieService
    private val localMovieStore: LocalMovieDataStore = LocalMovieDataStoreImpl()

    override fun discoverMovies(page: Int, sortBy: String): Single<DiscoverMovieResponse> {
        return movieService.discoverMovies(page, sortBy)
    }

    override fun getFavourites(): Single<List<MovieEntity>> {
        return localMovieStore.getFavourites()
    }

    override fun addFavourite(movie: MovieEntity): Single<Unit> {
        return localMovieStore.addFavourite(movie)
    }

    override fun removeFavourite(movieId: Long): Single<MovieEntity> {
        return localMovieStore.removeFavourite(movieId)
    }

    override fun isFavourite(movieId: Long): Single<Boolean> {
        return localMovieStore.isFavourite(movieId)
    }
}
