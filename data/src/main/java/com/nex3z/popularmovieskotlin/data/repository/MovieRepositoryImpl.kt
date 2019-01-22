package com.nex3z.popularmovieskotlin.data.repository

import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieResp
import com.nex3z.popularmovieskotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmovieskotlin.data.entity.review.GetMovieReviewsResp
import com.nex3z.popularmovieskotlin.data.entity.video.GetMovieVideosResp
import com.nex3z.popularmovieskotlin.data.local.MovieDao
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.net.service.MovieService
import io.reactivex.Maybe
import io.reactivex.Single

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    restClient: RestClient
) : MovieRepository {

    private val movieService: MovieService = restClient.movieService

    override fun discoverMovies(params: DiscoverMovieParams): Single<DiscoverMovieResp> {
        return movieService.discoverMovies(params.params)
    }

    override fun getVideos(movieId: Long): Single<GetMovieVideosResp> {
        return movieService.getVideos(movieId)
    }

    override fun getReviews(movieId: Long): Single<GetMovieReviewsResp> {
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