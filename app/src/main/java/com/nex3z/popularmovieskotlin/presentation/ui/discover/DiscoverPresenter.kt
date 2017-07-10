package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.util.Log
import android.view.View
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.movie.DiscoverMoviesUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BasePresenter

class DiscoverPresenter(
        private val discoverMoviesUseCase: DiscoverMoviesUseCase
) : BasePresenter<DiscoverView>() {

    private var page = 1
    private val movies: MutableList<MovieModel> = mutableListOf()

    override fun destroy() {
        super.destroy()
        discoverMoviesUseCase.dispose()
    }

    fun init() {
        view?.showLoading()
        refresh()
    }

    fun refresh() {
        page = 1
        movies.clear()
        view?.renderMovies(movies)
        fetchMovies()
    }

    fun loadMore() {
        page++
        fetchMovies()
    }

    fun onMovieClick(position: Int, poster: View) {
        view?.showDetail(movies[position], poster)
    }

    private fun fetchMovies() {
        Log.v(LOG_TAG, "fetchMovies(): page = " + page)
        discoverMoviesUseCase.execute(DiscoverMovieObserver(),
                DiscoverMoviesUseCase.Params.forPage(page));
    }

    private fun renderMovies(newMovies: List<MovieModel>) {
        Log.v(LOG_TAG, "renderMovies(): Showing " + newMovies.size
                + " more newMovies, total = " + movies.size)

        val currentSize = movies.size
        movies.addAll(newMovies)
        if (currentSize == 0) {
            view?.renderMovies(movies)
        } else {
            view?.renderMovies(movies, currentSize, newMovies.size)
        }
    }

    private inner class DiscoverMovieObserver : DefaultObserver<List<MovieModel>>() {
        override fun onNext(data: List<MovieModel>) {
            view?.hideLoading()
            if (!data.isEmpty()) {
                renderMovies(data)
            } else {
                page--
            }
        }

        override fun onError(throwable: Throwable) {
            view?.hideLoading()
            view?.showError(throwable.message ?: "")
            throwable.printStackTrace()
        }
    }

    companion object {
        private val LOG_TAG = "DiscoverPresenter"
    }

}