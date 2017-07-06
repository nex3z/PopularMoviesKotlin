package com.nex3z.popularmovieskotlin.domain.interactor.movie

import com.nex3z.popularmovieskotlin.data.repository.MovieRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModelMapper
import io.reactivex.Observable

class DiscoverMovieUseCase(
        private val movieRepository: MovieRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<List<MovieModel>, DiscoverMovieUseCase.Params>(threadExecutor, postExecutionThread) {

    private val mMovieModels: List<MovieModel>? = null

    override fun buildUseCaseObservable(params: Params): Observable<List<MovieModel>> {
        return movieRepository
                .discoverMovies(params.page, params.sortBy)
                .map(MovieModelMapper::transform)
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
