package com.nex3z.popularmoviekotlin.discover

import android.util.Log
import android.view.View
import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.DiscoverMovieUseCase
import com.nex3z.popularmoviekotlin.domain.interactor.SetFavouriteMovieUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

class DiscoverMoviePresenter : BasePresenter<DiscoverMovieView>() {

    private val discoverMovieUseCase: DiscoverMovieUseCase
            = App.service.create(::DiscoverMovieUseCase)

    private val setFavouriteMovieUseCase: SetFavouriteMovieUseCase
            = App.service.create(::SetFavouriteMovieUseCase)

    private var page: Int = FIRST_PAGE
    private val movies: MutableList<MovieModel> = mutableListOf()

    override fun destroy() {
        discoverMovieUseCase.dispose()
        setFavouriteMovieUseCase.dispose()
        super.destroy()
    }

    fun init() {
        view?.renderMovies(movies)
        refreshMovie()
    }

    fun onMovieClicked(position: Int, poster: View) {
        if (position >= 0 && position < movies.size) {
            view?.renderMovieDetail(movies[position], poster)
        }
    }

    fun toggleFavourite(position: Int) {
        val movie = movies[position]
        setFavouriteMovieUseCase.execute(SetFavoriteObserver(movie, position),
                SetFavouriteMovieUseCase.Params(movie, !movie.favourite))
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

    private inner class SetFavoriteObserver
        internal constructor(val movie: MovieModel, val position: Int)
        : DefaultObserver<Boolean>() {

        override fun onNext(data: Boolean) {
            super.onNext(data)
            movie.favourite = data
            if (position < movies.size && movies[position].id == movie.id) {
                view?.notifyMovieChanged(position)
            }
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.showError(throwable.message)
        }
    }

    companion object {
        private val LOG_TAG = DiscoverMoviePresenter::class.java.simpleName
        private val FIRST_PAGE = 1
    }
}