package com.nex3z.popularmoviekotlin.discover

import android.util.Log
import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.DiscoverMoviesUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

class DiscoverMoviePresenter : BasePresenter<DiscoverMovieView>() {

    private val discoverMovieUseCase: DiscoverMoviesUseCase
            = App.service.create(DiscoverMoviesUseCase::class)

    private var page: Int = FIRST_PAGE
    private val movies: MutableList<MovieModel> = mutableListOf()

    override fun destroy() {
        super.destroy()
        discoverMovieUseCase.dispose()
    }

    fun init() {
        view?.renderMovies(movies)
        refreshMovie()
    }

    fun onMovieClicked(position: Int) {
        if (position >= 0 && position < movies.size) {
            view?.renderMovieDetail(movies.get(position))
        }
    }

    fun toggleFavourite(position: Int) {

    }

    fun refreshMovie() {
        movies.clear()
        view?.notifyMovieChanged()
        fetchMovies(FIRST_PAGE)
    }

    fun loadMoreMovie() {
        fetchMovies(page + 1)
    }

    private fun fetchMovies(page: Int) {
        discoverMovieUseCase.execute(MovieObserver(page),
                DiscoverMovieParams(page, DiscoverMovieParams.SortBy.POPULARITY_DESC)
        )
    }

    private inner class MovieObserver internal constructor(private val page: Int)
        : DefaultObserver<List<MovieModel>>() {

        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<MovieModel>) {
            super.onNext(movies)
            Log.v(LOG_TAG, "onNext(): page = " + page + ", size = " + data.size)

            val start = movies.size
            movies.addAll(data)
            view?.notifyMovieInserted(start, movies.size)

            view?.hideLoading()
            this@DiscoverMoviePresenter.page = page
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message)
        }
    }

    companion object {
        private val LOG_TAG = DiscoverMoviePresenter::class.java.simpleName
        private val FIRST_PAGE = 1
    }
}