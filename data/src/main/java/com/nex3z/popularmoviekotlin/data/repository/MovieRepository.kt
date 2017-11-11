package com.nex3z.popularmoviekotlin.data.repository

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import io.reactivex.Maybe
import io.reactivex.Single

interface MovieRepository {

    fun discoverMovies(params: DiscoverMovieParams): Single<DiscoverMovieResponse>

    fun getVideos(movieId: Long): Single<GetMovieVideosResponse>

    fun getReviews(movieId: Long): Single<GetMovieReviewsResponse>

    fun getFavouriteMovies(): Single<List<MovieEntity>>

    fun getFavouriteMovieById(movieId: Long): Maybe<MovieEntity>

    fun isFavouriteMovie(movieId: Long): Single<Boolean>

    fun addMovieToFavourite(movie: MovieEntity)

    fun removeMovieFromFavourite(movie: MovieEntity)

}