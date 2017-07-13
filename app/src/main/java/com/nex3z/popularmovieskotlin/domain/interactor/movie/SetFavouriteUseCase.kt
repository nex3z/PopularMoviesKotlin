package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.data.repository.movie.MovieRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModelMapper
import io.reactivex.Observable

class SetFavouriteUseCase(
        private val movieRepository: MovieRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<MovieModel, SetFavouriteUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: SetFavouriteUseCase.Params): Observable<MovieModel> {
        return if (params.addToFavourite) {
            movieRepository
                    .addFavourite(MovieModelMapper.toEntity(params.movie))
                    .map { _ ->  params.movie.copy(favourite = params.addToFavourite)}
                    .toObservable()
        } else {
            movieRepository
                    .removeFavourite(params.movie.id)
                    .map { _ ->  params.movie.copy(favourite = params.addToFavourite)}
                    .toObservable()
        }
    }

    class Params private constructor(
            val movie: MovieModel,
            val addToFavourite: Boolean) {

        companion object {
            fun addToFavourite(movieModel: MovieModel): Params {
                return Params(movieModel, true)
            }

            fun removeFromFavourite(movieModel: MovieModel): Params {
                return Params(movieModel, false)
            }
        }
    }
}