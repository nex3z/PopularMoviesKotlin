package com.nex3z.popularmovieskotlin.data.repository

import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieResp
import com.nex3z.popularmovieskotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmovieskotlin.data.entity.review.GetMovieReviewsResp
import com.nex3z.popularmovieskotlin.data.entity.video.GetMovieVideosResp
import io.reactivex.Maybe
import io.reactivex.Single

interface MovieRepository {

    fun discoverMovies(params: DiscoverMovieParams): Single<DiscoverMovieResp>

    fun getVideos(movieId: Long): Single<GetMovieVideosResp>

    fun getReviews(movieId: Long): Single<GetMovieReviewsResp>

    fun getFavouriteMovies(): Single<List<MovieEntity>>

    fun getFavouriteMovieById(movieId: Long): Maybe<MovieEntity>

    fun isFavouriteMovie(movieId: Long): Single<Boolean>

    fun addMovieToFavourite(movie: MovieEntity)

    fun removeMovieFromFavourite(movie: MovieEntity)

}