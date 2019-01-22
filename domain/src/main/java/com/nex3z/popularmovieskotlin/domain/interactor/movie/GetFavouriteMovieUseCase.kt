package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.domain.Context
import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.transform
import io.reactivex.Observable

class GetFavouriteMovieUseCase(context: Context)
    : BaseUseCase<List<MovieModel>, Any?>(context) {

    override fun buildUseCaseObservable(params: Any?): Observable<List<MovieModel>> {
        return context.movieRepository
            .getFavouriteMovies()
            .map{ movies -> movies.map { it.transform(true) } }
            .toObservable()
    }

}