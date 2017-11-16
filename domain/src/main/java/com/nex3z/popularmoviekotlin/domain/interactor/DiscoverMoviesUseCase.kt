package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.domain.Context
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.movie.transform
import io.reactivex.Observable

class DiscoverMoviesUseCase(context: Context)
    : BaseUseCase<List<MovieModel>, DiscoverMovieParams>(context) {

    override fun buildUseCaseObservable(params: DiscoverMovieParams): Observable<List<MovieModel>> {
        return context.movieRepository
                .discoverMovies(params)
                .map(::transform)
                .toObservable()
    }
}