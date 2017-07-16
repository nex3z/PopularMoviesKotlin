package com.nex3z.popularmovieskotlin.presentation.ui.movielist.discover

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.data.repository.movie.MovieRepository
import com.nex3z.popularmovieskotlin.data.repository.movie.MovieRepositoryImpl
import com.nex3z.popularmovieskotlin.domain.executor.JobExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.movie.DiscoverMoviesUseCase
import com.nex3z.popularmovieskotlin.domain.interactor.movie.SetFavouriteUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.UiThread
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseFragment
import com.nex3z.popularmovieskotlin.presentation.ui.base.HasPresenter
import com.nex3z.popularmovieskotlin.presentation.ui.misc.SpacesItemDecoration
import com.nex3z.popularmovieskotlin.presentation.ui.misc.ViewUtil
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.MovieAdapter
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.MovieListView
import com.nex3z.popularmovieskotlin.presentation.ui.movielist.OnMovieSelectListener
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : BaseFragment(), MovieListView, HasPresenter<DiscoverPresenter> {

    private lateinit var presenter: DiscoverPresenter
    private var listener: OnMovieSelectListener? = null
    private val adapter: MovieAdapter = MovieAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMovieSelectListener) {
            listener = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun getPresenter(): DiscoverPresenter {
        return presenter
    }

    override fun showLoading() {
        pb_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_loading.visibility = View.GONE
        swipe_container.isRefreshing = false
    }

    override fun renderMovies(movies: List<MovieModel>) {
        adapter.movies = movies
    }

    override fun renderMovies(movies: List<MovieModel>, start: Int, count: Int) {
        adapter.notifyItemRangeInserted(start, count)
    }

    override fun notifyUpdate(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun showDetail(movie: MovieModel, poster: View) {
        listener?.onMovieSelect(movie, poster)
    }

    private fun init() {
        initView()
        initPresenter()
        presenter.init()
    }

    private fun initView() {
        adapter.onItemClickListener = object: MovieAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, poster: View) {
                presenter.onMovieClick(position, poster)
            }

            override fun onFavouriteClick(position: Int) {
                presenter.onFavouriteClick(position)
            }
        }

        rv_movie_list.adapter = adapter
        rv_movie_list.layoutManager = GridLayoutManager(context, 2)
        val spacing = ViewUtil.dpToPx(context, 4.0f).toInt()
        rv_movie_list.addItemDecoration(SpacesItemDecoration(spacing, spacing, spacing, spacing))
        rv_movie_list.setHasFixedSize(true)

        swipe_container.setOnRefreshListener({ direction ->
            when (direction) {
                SwipyRefreshLayoutDirection.TOP -> presenter.refresh()
                SwipyRefreshLayoutDirection.BOTTOM -> presenter.loadMore()
                else -> { }
            }
        })
    }

    private fun initPresenter() {
        val movieRepo : MovieRepository = MovieRepositoryImpl(App.restClient)
        val discoverUseCase = DiscoverMoviesUseCase(movieRepo, JobExecutor(), UiThread)
        val setFavouriteUseCase = SetFavouriteUseCase(movieRepo, JobExecutor(), UiThread)
        presenter = DiscoverPresenter(discoverUseCase, setFavouriteUseCase)
        presenter.view = this
    }

    companion object {
        private val LOG_TAG = "DiscoverFragment"

        fun newInstance(): DiscoverFragment {
            return DiscoverFragment()
        }
    }
}
