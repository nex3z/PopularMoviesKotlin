package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.domain.Context
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.movie.transform
import io.reactivex.Observable

class SetFavouriteMovieUseCase(context: Context)
    : BaseUseCase<Boolean, SetFavouriteMovieUseCase.Params>(context) {

    override fun buildUseCaseObservable(params: Params): Observable<Boolean> {
        return Observable.create {
            if (params.favourite) {
                context.movieRepository.addMovieToFavourite(params.entity)
            } else {
                context.movieRepository.removeMovieFromFavourite(params.entity)
            }
            it.onNext(params.favourite)
            it.onComplete()
        }
    }

    class Params(movie: MovieModel, val favourite: Boolean) {

        val entity: MovieEntity = movie.transform()

    }
}