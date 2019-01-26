package com.nex3z.popularmovieskotlin.app.discover

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieParams
import com.nex3z.popularmovieskotlin.domain.PopMovieService
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.movie.DiscoverMovieUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel

class DiscoverMovieViewModel(
    application: Application,
    service: PopMovieService
) : AndroidViewModel(application) {
    private val discoverMovieUseCase: DiscoverMovieUseCase = service.create(::DiscoverMovieUseCase)

    private val _movies = MutableLiveData<MovieListChangedEvent>()
    private var currentPage: Int = FIRST_PAGE

    val movies: LiveData<MovieListChangedEvent> = _movies

    fun refresh() {
        _movies.value = MovieListRefreshEvent(listOf())
        fetch(FIRST_PAGE)
    }

    fun fetchMore() = fetch(currentPage + 1)

    private fun fetch(page: Int) {
        Log.v(LOG_TAG, "fetch(): page = $page")
        val params = DiscoverMovieParams(page = page, sortBy = DiscoverMovieParams.SortBy.POPULARITY_DESC)
        discoverMovieUseCase.execute(MovieObserver(page), params)
    }

    private inner class MovieObserver(private val page: Int) : DefaultObserver<List<MovieModel>>() {
        override fun onNext(data: List<MovieModel>) {
            if (page == FIRST_PAGE) {
                _movies.value = MovieListRefreshEvent(data)
                currentPage = page
            } else if (data.isNotEmpty()) {
                _movies.value = MovieListAppendEvent(data)
                currentPage = page
            }
        }
    }

    companion object {
        private val LOG_TAG: String = DiscoverMovieViewModel::class.java.simpleName
        private const val FIRST_PAGE: Int = 1
    }
}
