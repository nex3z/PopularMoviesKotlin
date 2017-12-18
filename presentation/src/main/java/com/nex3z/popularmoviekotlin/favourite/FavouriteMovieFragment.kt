package com.nex3z.popularmoviekotlin.favourite

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.base.BaseFragment
import com.nex3z.popularmoviekotlin.base.HasPresenter
import com.nex3z.popularmoviekotlin.discover.MovieAdapter
import com.nex3z.popularmoviekotlin.discover.MovieDetailNavigator
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.misc.SpacingItemDecoration
import com.nex3z.popularmoviekotlin.util.dpToPx
import kotlinx.android.synthetic.main.fragment_favourite_movie.*

class FavouriteMovieFragment :
        BaseFragment(), FavouriteMovieVIew, HasPresenter<FavouriteMoviePresenter> {

    private var navigator: MovieDetailNavigator? = null
    private lateinit var presenter: FavouriteMoviePresenter
    private val adapter: MovieAdapter = MovieAdapter()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_favourite_movie, container, false)
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

    override fun getPresenter(): FavouriteMoviePresenter = presenter

    override fun showLoading() {
        pb_favourite_movie_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_favourite_movie_loading.visibility = View.GONE
    }

    override fun renderMovies(movies: List<MovieModel>) {
        adapter.movies = movies
    }

    override fun notifyMovieChanged(position: Int) {
        adapter.notifyItemChanged(position)
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
        adapter.onMovieClickListener = object: MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(position: Int) {
                presenter.onMovieClicked(position)
            }
            override fun onFavouriteClick(position: Int) {
                presenter.toggleFavourite(position)
            }
        }

        with(rv_favourite_movie_list) {
            adapter = this@FavouriteMovieFragment.adapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            addItemDecoration(SpacingItemDecoration(dpToPx(4.0f).toInt()))
        }
    }

    private fun initPresenter() {
        presenter = FavouriteMoviePresenter();
        presenter.view = this
    }

    companion object {

        fun newInstance(): FavouriteMovieFragment = FavouriteMovieFragment()

    }
}
