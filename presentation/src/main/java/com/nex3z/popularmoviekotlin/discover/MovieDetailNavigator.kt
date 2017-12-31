package com.nex3z.popularmoviekotlin.discover

import android.view.View
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

interface MovieDetailNavigator {

    fun navigateToDetail(movie: MovieModel, poster: View)

}