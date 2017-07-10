package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.view.View
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseView
import com.nex3z.popularmovieskotlin.presentation.ui.base.LoadDataView

interface DiscoverView : BaseView, LoadDataView {

    fun renderMovies(movies: List<MovieModel>)

    fun renderMovies(movies: List<MovieModel>, start: Int, count: Int)

    fun notifyUpdate(position: Int)

    fun showDetail(movie: MovieModel, poster: View)

}