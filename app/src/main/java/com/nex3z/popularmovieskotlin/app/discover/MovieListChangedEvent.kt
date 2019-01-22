package com.nex3z.popularmovieskotlin.app.discover

import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel

sealed class MovieListChangedEvent(
    val movies: List<MovieModel>
)

class MovieListAppendEvent(
    movies: List<MovieModel>
) : MovieListChangedEvent(movies)

class MovieListRefreshEvent(
    orders: List<MovieModel>
) : MovieListChangedEvent(orders)
