package com.nex3z.popularmoviekotlin.detail.info

import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

class MovieInfoPresenter(val movie: MovieModel) : BasePresenter<MovieInfoView>() {

    fun init() {
        view?.renderMovie(movie)
    }

}