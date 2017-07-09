package com.nex3z.popularmovieskotlin.presentation.ui.detail.review

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.data.repository.review.ReviewRepository
import com.nex3z.popularmovieskotlin.data.repository.review.ReviewRepositoryImpl
import com.nex3z.popularmovieskotlin.domain.executor.JobExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.review.GetReviewsUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import com.nex3z.popularmovieskotlin.presentation.ui.UiThread
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseFragment
import com.nex3z.popularmovieskotlin.presentation.ui.base.HasPresenter
import kotlinx.android.synthetic.main.content_movie_review.*

class MovieReviewFragment : BaseFragment(), ReviewView, HasPresenter<ReviewPresenter> {

    private lateinit var movie: MovieModel
    private val presenter: ReviewPresenter
    private val adapter: ReviewAdapter = ReviewAdapter()

    init {
        val reviewRepo : ReviewRepository = ReviewRepositoryImpl(App.restClient)
        val useCase = GetReviewsUseCase(reviewRepo, JobExecutor(), UiThread)
        presenter = ReviewPresenter(useCase)
        presenter.view = this
    }

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
        pb_load_review.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_load_review.visibility = View.GONE
    }

    override fun renderReviews(reviews: List<ReviewModel>) {
        adapter.reviews = reviews
    }

    override fun getPresenter(): ReviewPresenter {
        return presenter
    }

    private fun init() {
        initView()
        presenter.init(movie)
    }

    private fun initView() {
        rv_review_list.adapter = adapter
        rv_review_list.layoutManager = LinearLayoutManager(context)
        rv_review_list.setHasFixedSize(true)
        rv_review_list.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    companion object {
        private val LOG_TAG = "MovieReviewFragment"
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
