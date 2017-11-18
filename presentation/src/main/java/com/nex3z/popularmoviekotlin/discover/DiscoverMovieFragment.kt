package com.nex3z.popularmoviekotlin.discover

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.base.BaseFragment
import com.nex3z.popularmoviekotlin.base.HasPresenter
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.misc.SpacingItemDecoration
import com.nex3z.popularmoviekotlin.util.ViewUtil
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_discover_movie.*

class DiscoverMovieFragment :
        BaseFragment(), DiscoverMovieView, HasPresenter<DiscoverMoviePresenter> {

    private var navigator: MovieDetailNavigator? = null
    private lateinit var presenter: DiscoverMoviePresenter
    private val adapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_discover_movie, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is MovieDetailNavigator) {
            navigator = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnMovieClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        navigator = null
    }

    override fun getPresenter(): DiscoverMoviePresenter = presenter

    override fun showLoading() {}

    override fun hideLoading() {
        swipy_discover_movie_list.isRefreshing = false
    }

    override fun renderMovies(movies: List<MovieModel>) {
        adapter.movies = movies
    }

    override fun notifyMovieInserted(position: Int, count: Int) {
        adapter.notifyItemRangeInserted(position, count)
    }

    override fun notifyMovieChanged(position: Int) {
        adapter.notifyItemChanged(position)
    }

    override fun notifyMovieChanged() {
        adapter.notifyDataSetChanged()
    }

    override fun renderMovieDetail(movie: MovieModel) {
        navigator?.navigateToDetail(movie)
    }

    private fun init() {
        initView()
        initPresenter()
        presenter.init()
    }

    private fun initView() {
        swipy_discover_movie_list.setOnRefreshListener {
            when (it) {
                SwipyRefreshLayoutDirection.TOP -> presenter.refreshMovie()
                SwipyRefreshLayoutDirection.BOTTOM -> presenter.loadMoreMovie()
                else -> {
                }
            }
        }

        adapter.onMovieClickListener = object: MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(position: Int) {
                presenter.onMovieClicked(position)
            }
            override fun onFavouriteClick(position: Int) {
                presenter.toggleFavourite(position)
            }
        }

        rv_discover_movie_list.adapter = adapter

        val layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rv_discover_movie_list.setLayoutManager(layoutManager)

        val decoration = SpacingItemDecoration(ViewUtil.dpToPx(4.0f).toInt())
        rv_discover_movie_list.addItemDecoration(decoration)
    }

    private fun initPresenter() {
        presenter = DiscoverMoviePresenter();
        presenter.view = this
    }

    companion object {

        fun newInstance(): DiscoverMovieFragment = DiscoverMovieFragment()

    }
}
