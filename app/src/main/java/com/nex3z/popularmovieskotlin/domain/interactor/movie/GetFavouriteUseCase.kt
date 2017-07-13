package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.data.repository.movie.MovieRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModelMapper
import io.reactivex.Observable

class GetFavouriteUseCase(
        private val movieRepository: MovieRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<List<MovieModel>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Observable<List<MovieModel>> {
        return movieRepository
                .getFavourites()
                .map(MovieModelMapper::transformFavourite)
                .toObservable()
    }
}