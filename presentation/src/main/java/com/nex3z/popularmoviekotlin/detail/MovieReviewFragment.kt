package com.nex3z.popularmoviekotlin.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

class MovieReviewFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_review, container, false)
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
