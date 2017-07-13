package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.data.repository.movie.MovieRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModelMapper
import io.reactivex.Observable

class DiscoverMoviesUseCase(
        private val movieRepository: MovieRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<List<MovieModel>, DiscoverMoviesUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<MovieModel>> {
        return movieRepository
                .discoverMovies(params.page, params.sortBy)
                .map(MovieModelMapper::transform)
                .toObservable()
                .flatMap({Observable.fromIterable(it)})
                .flatMap(this::isFavourite, this::update)
                .toList()
                .toObservable()
    }

    private fun isFavourite(movieModel: MovieModel): Observable<Boolean> {
        return movieRepository.isFavourite(movieModel.id).toObservable()
    }

    private fun update(movieModel: MovieModel, favourite: Boolean): MovieModel {
        movieModel.favourite = favourite
        return movieModel
    }

    class Params private constructor(val page: Int, val sortBy: String) {
        companion object {
            val SORT_BY_POPULARITY_DESC = "popularity.desc"
            val SORT_BY_VOTE_AVERAGE_DESC = "vote_average.desc"
            val SORT_BY_VOTE_COUNT_DESC = "vote_count.desc"
            val SORT_BY_RELEASE_DATE_DESC = "release_date.desc"

            fun forPage(page: Int, sortBy: String = SORT_BY_POPULARITY_DESC): Params {
                return Params(page, sortBy)
            }
        }
    }
}
