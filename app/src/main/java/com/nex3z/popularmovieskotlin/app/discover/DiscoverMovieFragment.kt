package com.nex3z.popularmovieskotlin.app.discover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.app.misc.EndlessScrollListener
import com.nex3z.popularmovieskotlin.app.misc.SpacingItemDecoration
import com.nex3z.popularmovieskotlin.app.misc.ViewModelFactory
import com.nex3z.popularmovieskotlin.app.misc.dpToPx
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import kotlinx.android.synthetic.main.discover_movie_content.*

class DiscoverMovieFragment : Fragment() {

    private lateinit var viewModel: DiscoverMovieViewModel
    private val movies: MutableList<MovieModel> = mutableListOf()
    private val adapter: MovieAdapter = MovieAdapter()
    private lateinit var endlessScrollListener: EndlessScrollListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.discover_movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(DiscoverMovieViewModel::class.java)
        initView()
        bindViewModel()
        viewModel.refresh()
    }

    private fun initView() {
        adapter.movies = movies
        adapter.onMovieClickListener = object : MovieAdapter.OnMovieClickListener {
            override fun onMovieClick(position: Int, poster: View) {
                Log.v(LOG_TAG, "onMovieClick(): position = $position")
                val movie = movies[position]
                val direction = DiscoverMovieFragmentDirections.actionDiscoverMovieFragmentToMovieDetailFragment(movie)
                findNavController().navigate(direction)
            }
            override fun onFavouriteClick(position: Int) {
                Log.v(LOG_TAG, "onFavouriteClick(): position = ${position}")
            }
        }

        with(rv_discover_movie_list) {
            this.adapter = this@DiscoverMovieFragment.adapter
            val layoutManager = GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
            this.layoutManager = layoutManager
            addItemDecoration(SpacingItemDecoration(dpToPx(4.0f).toInt()))

            endlessScrollListener = EndlessScrollListener(layoutManager)
            endlessScrollListener.onLoadMore = {
                viewModel.fetchMore()
            }
            addOnScrollListener(endlessScrollListener)
        }

        srl_discover_movie_refresh.setColorSchemeColors(*resources.getIntArray(R.array.swipe_refresh_colors))
        srl_discover_movie_refresh.setOnRefreshListener { viewModel.refresh() }
    }

    private fun bindViewModel() {
        viewModel.movies.observe(this, OnMovieListChangedObserver())
    }

    inner class OnMovieListChangedObserver : Observer<MovieListChangedEvent> {
        override fun onChanged(t: MovieListChangedEvent?) {
            srl_discover_movie_refresh.isRefreshing = false
            Log.v(LOG_TAG, "onChanged(): movies = ${t?.movies}")
            when (t) {
                is MovieListAppendEvent -> {
                    val start = movies.size
                    val count = t.movies.size
                    movies.addAll(t.movies)
                    adapter.notifyItemRangeInserted(start, count)
                    endlessScrollListener.loading = false
                }
                is MovieListRefreshEvent -> {
                    movies.clear()
                    movies.addAll(t.movies)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    companion object {
        val LOG_TAG: String = DiscoverMovieFragment::class.java.simpleName
    }
}
