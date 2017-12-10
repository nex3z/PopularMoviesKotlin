package com.nex3z.popularmoviekotlin.favourite

import android.util.Log
import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.GetFavouriteMovieUseCase
import com.nex3z.popularmoviekotlin.domain.interactor.SetFavouriteMovieUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

class FavouriteMoviePresenter : BasePresenter<FavouriteMovieVIew>() {

    private val getFavouriteMovieUseCase: GetFavouriteMovieUseCase
            = App.service.create(::GetFavouriteMovieUseCase)
    private val setFavouriteMovieUseCase: SetFavouriteMovieUseCase
            = App.service.create(::SetFavouriteMovieUseCase)

    private val movies: MutableList<MovieModel> = mutableListOf()

    override fun destroy() {
        getFavouriteMovieUseCase.dispose()
        setFavouriteMovieUseCase.dispose()
        super.destroy()
    }

    fun init() {
        refresh()
    }

    fun refresh() {
        movies.clear()
        view?.renderMovies(movies)
        getFavouriteMovieUseCase.execute(FavouriteMovieObserver(), Unit)
    }

    fun onMovieClicked(position: Int) {
        if (position >= 0 && position < movies.size) {
            view?.renderMovieDetail(movies[position])
        }
    }

    fun toggleFavourite(position: Int) {
        val movie = movies[position]
        setFavouriteMovieUseCase.execute(SetFavoriteObserver(movie, position),
                SetFavouriteMovieUseCase.Params(movie, !movie.favourite))
    }

    private inner class FavouriteMovieObserver : DefaultObserver<List<MovieModel>>() {

        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<MovieModel>) {
            super.onNext(data)
            view?.hideLoading()
            movies.addAll(data)
            view?.renderMovies(data)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message)
            Log.e("XXX", "onError()", throwable)
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
}