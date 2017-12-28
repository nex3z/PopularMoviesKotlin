package com.nex3z.popularmoviekotlin.data.repository

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import com.nex3z.popularmoviekotlin.data.local.room.MovieDao
import com.nex3z.popularmoviekotlin.data.net.RestClient
import com.nex3z.popularmoviekotlin.data.net.service.MovieService
import io.reactivex.Maybe
import io.reactivex.Single

class MovieRepositoryImpl(
        private val movieDao: MovieDao,
        restClient: RestClient)
    : MovieRepository {

    private val movieService: MovieService = restClient.movieService

    override fun discoverMovies(params: DiscoverMovieParams): Single<DiscoverMovieResponse> {
        return movieService.discoverMovies(params.params)
    }

    override fun getVideos(movieId: Long): Single<GetMovieVideosResponse> {
        return movieService.getVideos(movieId)
    }

    override fun getReviews(movieId: Long): Single<GetMovieReviewsResponse> {
        return movieService.getReviews(movieId)
    }

    override fun getFavouriteMovies(): Single<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override fun getFavouriteMovieById(movieId: Long): Maybe<MovieEntity> {
        return movieDao.getMovieById(movieId)
    }

    override fun isFavouriteMovie(movieId: Long): Single<Boolean> {
        return movieDao.getMovieById(movieId)
                .map { true }
                .switchIfEmpty(Single.defer{ Single.just(false) })
    }

    override fun addMovieToFavourite(movie: MovieEntity) {
        return movieDao.insert(movie)
    }

    override fun removeMovieFromFavourite(movie: MovieEntity) {
        return movieDao.delete(movie)
    }

}