package com.nex3z.popularmoviekotlin.data.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import com.nex3z.popularmoviekotlin.data.local.MovieDatabase
import com.nex3z.popularmoviekotlin.data.net.RestClient
import com.nex3z.popularmoviekotlin.data.net.service.MovieService
import io.reactivex.Maybe
import io.reactivex.Single

class MovieRepositoryImpl(context: Context, restClient: RestClient) : MovieRepository {

    private val movieService: MovieService = restClient.movieService

    private val movieDatabase: MovieDatabase = Room.databaseBuilder(
            context.applicationContext, MovieDatabase::class.java, "PopularMovie.db").build()

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
        return movieDatabase.movieDao().getMovies()
    }

    override fun getFavouriteMovieById(movieId: Long): Maybe<MovieEntity> {
        return movieDatabase.movieDao().getMovieById(movieId)
    }

    override fun isFavouriteMovie(movieId: Long): Single<Boolean> {
        return movieDatabase.movieDao().checkMovieById(movieId).map{ it > 0 }
    }

    override fun addMovieToFavourite(movie: MovieEntity) {
        return movieDatabase.movieDao().insert(movie)
    }

    override fun removeMovieFromFavourite(movie: MovieEntity) {
        return movieDatabase.movieDao().delete(movie)
    }

}