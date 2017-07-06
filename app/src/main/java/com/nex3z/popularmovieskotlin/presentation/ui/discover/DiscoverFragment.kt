package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.data.repository.MovieRepository
import com.nex3z.popularmovieskotlin.data.repository.MovieRepositoryImpl
import com.nex3z.popularmovieskotlin.domain.executor.JobExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.movie.DiscoverMovieUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.UiThread
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseFragment
import com.nex3z.popularmovieskotlin.presentation.ui.misc.SpacesItemDecoration
import com.nex3z.popularmovieskotlin.presentation.ui.misc.ViewUtil
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : BaseFragment(), DiscoverView {

    private lateinit var presenter: DiscoverPresenter
    private var type: String = TYPE_DISCOVERY
    private var listener: OnMovieSelectListener? = null
    private val adapter: MovieAdapter = MovieAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnMovieSelectListener) {
            listener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            type = arguments.getString(ARG_TYPE)
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

    override fun showDetail(movie: MovieModel) {
        listener?.onMovieSelect(movie)
    }

    private fun init() {
        initView()
        initPresenter()
        presenter.init()
    }

    private fun initView() {
        rv_movie_list.adapter = adapter
        rv_movie_list.layoutManager = GridLayoutManager(context, 2)
        val spacing = ViewUtil.dpToPx(context, 4.0f).toInt()
        val rightSpacing = ViewUtil.dpToPx(context, 2.0f).toInt()
        rv_movie_list.addItemDecoration(
                SpacesItemDecoration(spacing, spacing, rightSpacing, spacing))
        rv_movie_list.setHasFixedSize(true)

        swipe_container.setOnRefreshListener({ direction ->
            when (direction) {
                SwipyRefreshLayoutDirection.TOP -> presenter.refresh()
                SwipyRefreshLayoutDirection.BOTTOM -> presenter.loadMore()
                else -> { }
            }
        })
    }

    fun initPresenter() {
        val movieRepo : MovieRepository = MovieRepositoryImpl(App.restClient)
        val useCase = DiscoverMovieUseCase(movieRepo, JobExecutor(), UiThread)
        presenter = DiscoverPresenter(useCase)
        presenter.view = this
    }

    interface OnMovieSelectListener {
        fun onMovieSelect(movie: MovieModel)
    }

    companion object {
        private val LOG_TAG = "DiscoverFragment"
        private val ARG_TYPE = "type"
        private val TYPE_DISCOVERY = "discovery"

        fun newDiscoverInstance(): DiscoverFragment {
            val fragment = DiscoverFragment()
            val args = Bundle()
            args.putString(ARG_TYPE, TYPE_DISCOVERY)
            fragment.arguments = args
            return fragment
        }
    }
}
