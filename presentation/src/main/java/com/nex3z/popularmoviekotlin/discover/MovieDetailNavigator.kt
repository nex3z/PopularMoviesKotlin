package com.nex3z.popularmoviekotlin.discover

import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

interface MovieDetailNavigator {

    fun navigateToDetail(movie: MovieModel)

}