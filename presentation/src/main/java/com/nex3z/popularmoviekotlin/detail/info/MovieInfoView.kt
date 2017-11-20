package com.nex3z.popularmoviekotlin.detail.info

import com.nex3z.popularmoviekotlin.base.BaseView
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

interface MovieInfoView : BaseView {

    fun renderMovie(movie: MovieModel)

}