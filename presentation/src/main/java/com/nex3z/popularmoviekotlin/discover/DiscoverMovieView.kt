package com.nex3z.popularmoviekotlin.discover

import com.nex3z.popularmoviekotlin.base.BaseView
import com.nex3z.popularmoviekotlin.base.LoadDataView
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

interface DiscoverMovieView : BaseView, LoadDataView {

    fun renderMovies(movies: List<MovieModel>)

    fun notifyMovieInserted(position: Int, count: Int)

    fun notifyMovieChanged(position: Int)

    fun notifyMovieChanged()

    fun renderMovieDetail(movie: MovieModel)

}