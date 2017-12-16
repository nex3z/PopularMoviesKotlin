package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.domain.Context
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.movie.transform
import io.reactivex.Observable

class GetFavouriteMovieUseCase(context: Context)
    : BaseUseCase<List<MovieModel>, Any?>(context) {

    override fun buildUseCaseObservable(params: Any?): Observable<List<MovieModel>> {
        return context.movieRepository
                .getFavouriteMovies()
                .map{ it.map { it.transform(true) } }
                .toObservable()
    }

}