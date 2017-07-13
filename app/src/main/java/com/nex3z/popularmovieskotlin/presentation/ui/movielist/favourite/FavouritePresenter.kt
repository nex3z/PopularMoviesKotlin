package com.nex3z.popularmovieskotlin.presentation.ui.movielist.favourite

import android.view.View
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.movie.GetFavouriteUseCase
import com.nex3z.popularmovieskotlin.domain.interactor.movie.SetFavouriteUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BasePresenter
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.MovieListView

class FavouritePresenter(
        private val getFavouriteUseCase: GetFavouriteUseCase,
        private val setFavouriteUseCase: SetFavouriteUseCase
) : BasePresenter<MovieListView>() {

    private var movies: MutableList<MovieModel> = mutableListOf()

    override fun destroy() {
        super.destroy()
        getFavouriteUseCase.dispose()
        setFavouriteUseCase.dispose()
    }

    fun init() {
        view?.showLoading()
        refresh()
    }

    fun refresh() {
        view?.renderMovies(movies)
        fetchMovies()
    }

    fun onMovieClick(position: Int, poster: View) {
        view?.showDetail(movies[position], poster)
    }

    fun onFavouriteClick(position: Int) {
        val movie = movies[position]
        val params = if (movie.favourite) SetFavouriteUseCase.Params.removeFromFavourite(movie)
        else SetFavouriteUseCase.Params.addToFavourite(movie)
        setFavouriteUseCase.execute(SetFavouriteMovieObserver(position), params)
    }

    private fun fetchMovies() {
        movies = mutableListOf()
        getFavouriteUseCase.execute(GetFavouriteMovieObserver(), null);
    }

    private inner class GetFavouriteMovieObserver : DefaultObserver<List<MovieModel>>() {
        override fun onNext(data: List<MovieModel>) {
            view?.hideLoading()
            if (!data.isEmpty()) {
                movies.addAll(data)
                view?.renderMovies(movies)
            }
        }

        override fun onError(throwable: Throwable) {
            view?.hideLoading()
            view?.showError(throwable.message ?: "")
            throwable.printStackTrace()
        }
    }

    private inner class SetFavouriteMovieObserver(
            private val position: Int
    ) : DefaultObserver<MovieModel>() {
        override fun onNext(data: MovieModel) {
            super.onNext(data)
            movies[position] = data
            view?.notifyUpdate(position)
        }

        override fun onError(throwable: Throwable) {
            view?.showError(throwable.message ?: "")
            throwable.printStackTrace()
        }
    }

    companion object {
        private val LOG_TAG = "FavouritePresenter"
    }
}