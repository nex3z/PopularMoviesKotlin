package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieResp
import com.nex3z.popularmovieskotlin.domain.Context
import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.transform
import io.reactivex.Observable

class DiscoverMovieUseCase(context: Context)
    : BaseUseCase<List<MovieModel>, DiscoverMovieParams>(context) {

    override fun buildUseCaseObservable(params: DiscoverMovieParams): Observable<List<MovieModel>> {
        return context.movieRepository
            .discoverMovies(params)
            .map(DiscoverMovieResp::transform)
            .flatMapObservable { Observable.fromIterable(it) }
            .flatMap(this::checkFavourite, this::setFavourite)
            .toList()
            .toObservable()
    }

    private fun checkFavourite(movie: MovieModel): Observable<Boolean>
            = context.movieRepository.isFavouriteMovie(movie.id).toObservable()

    private fun setFavourite(movie: MovieModel, isFavourite: Boolean): MovieModel
            = movie.apply { favourite = isFavourite }

}