package com.nex3z.popularmovieskotlin.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseFragment
import com.nex3z.popularmovieskotlin.presentation.ui.detail.info.MovieInfoFragment
import com.nex3z.popularmovieskotlin.presentation.ui.detail.review.MovieReviewFragment
import com.nex3z.popularmovieskotlin.presentation.ui.detail.video.MovieVideoFragment

class MovieDetailFragment : BaseFragment() {

    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movie = arguments.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.container_movie_detail_info, MovieInfoFragment.newInstance(movie))
        transaction.add(R.id.container_movie_detail_video, MovieVideoFragment.newInstance(movie))
        transaction.add(R.id.container_movie_detail_review, MovieReviewFragment.newInstance(movie))
        transaction.commit()
    }

    companion object {
        private val LOG_TAG = "MovieDetailFragment"
        private val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: MovieModel): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            fragment.arguments = args
            return fragment
        }
    }
}
