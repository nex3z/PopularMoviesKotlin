package com.nex3z.popularmoviekotlin.detail.review

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.base.BaseFragment
import com.nex3z.popularmoviekotlin.base.HasPresenter
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel
import kotlinx.android.synthetic.main.fragment_movie_review.*

class MovieReviewFragment : BaseFragment(), MovieReviewView, HasPresenter<MovieReviewPresenter> {

    private lateinit var movie: MovieModel
    private lateinit var presenter: MovieReviewPresenter
    private val adapter: ReviewAdapter = ReviewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movie = arguments.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun showLoading() {
        pb_movie_review_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_movie_review_loading.visibility = View.GONE
    }

    override fun getPresenter(): MovieReviewPresenter = presenter

    override fun renderReviews(reviews: List<ReviewModel>) {
        adapter.reviews = reviews
    }

    private fun init() {
        initView()
        initPresenter()
        presenter.init()
    }

    private fun initView() {
        with(rv_movie_review_list) {
            adapter = this@MovieReviewFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initPresenter() {
        presenter = MovieReviewPresenter(movie)
        presenter.view = this
    }

    companion object {
        private val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: MovieModel): MovieReviewFragment {
            val fragment = MovieReviewFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            fragment.arguments = args
            return fragment
        }
    }
}
