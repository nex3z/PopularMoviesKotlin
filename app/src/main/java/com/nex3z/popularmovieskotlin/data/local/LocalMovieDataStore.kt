package com.nex3z.popularmovieskotlin.data.local

import com.nex3z.popularmovieskotlin.data.entity.movie.MovieEntity
import io.reactivex.Single

interface LocalMovieDataStore {

    fun getFavourites(): Single<List<MovieEntity>>

    fun addFavourite(movie: MovieEntity): Single<Unit>

    fun removeFavourite(movieId: Long): Single<MovieEntity>

    fun isFavourite(movieId: Long): Single<Boolean>

}