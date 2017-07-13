package com.nex3z.popularmovieskotlin.presentation.ui.movielist

import android.view.View
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel

interface OnMovieSelectListener {

    fun onMovieSelect(movie: MovieModel, poster: View)

}