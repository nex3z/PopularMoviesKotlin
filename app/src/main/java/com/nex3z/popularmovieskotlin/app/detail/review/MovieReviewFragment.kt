package com.nex3z.popularmovieskotlin.app.detail.review

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.app.misc.ViewModelFactory
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import kotlinx.android.synthetic.main.fragment_movie_review.*

class MovieReviewFragment : Fragment() {

    private lateinit var viewModel: MovieReviewViewModel
    private val adapter: ReviewAdapter = ReviewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: MovieModel = arguments?.getParcelable(ARG_MOVIE) ?: throw IllegalStateException("movie is missing")
        Log.v(LOG_TAG, "onViewCreated(): movie = $movie")
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MovieReviewViewModel::class.java)
        viewModel.movie = movie
        initView()
        bindViewModel()
        viewModel.fetchVideos()
    }

    private fun initView() {
        with(rv_movie_review_list) {
            adapter = this@MovieReviewFragment.adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun bindViewModel() {
        viewModel.reviews.observe(this, OnReviewListChangedObserver())
    }

    inner class OnReviewListChangedObserver : Observer<List<ReviewModel>> {
        override fun onChanged(t: List<ReviewModel>?) {
            t?.let { adapter.reviews = it }
        }
    }

    companion object {
        private val LOG_TAG: String = MovieReviewFragment::class.java.simpleName
        private const val ARG_MOVIE = "movie"

        @JvmStatic
        fun newInstance(movie: MovieModel) = MovieReviewFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
        }
    }
}
