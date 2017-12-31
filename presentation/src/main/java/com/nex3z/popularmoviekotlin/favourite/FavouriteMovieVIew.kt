package com.nex3z.popularmoviekotlin.favourite

import android.view.View
import com.nex3z.popularmoviekotlin.base.BaseView
import com.nex3z.popularmoviekotlin.base.LoadDataView
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

interface FavouriteMovieVIew : BaseView, LoadDataView {

    fun renderMovies(movies: List<MovieModel>)

    fun notifyMovieChanged(position: Int)

    fun renderMovieDetail(movie: MovieModel, poster: View)

}