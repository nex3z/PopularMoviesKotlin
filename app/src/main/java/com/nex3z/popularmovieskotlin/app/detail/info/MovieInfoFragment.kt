package com.nex3z.popularmovieskotlin.app.detail.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.app.misc.getNames
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_info.*

class MovieInfoFragment : Fragment() {

    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_MOVIE) ?: throw IllegalStateException("movie cannot be null")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        renderMovie()
    }

    private fun renderMovie() {
        tv_item_movie_vote_average.text = movie.voteAverage.toString()
        tv_item_movie_release_date.text = movie.releaseDate
        tv_item_movie_genre.text = movie.genreIds.getNames()
        tv_movie_info_overview.text = movie.overview
        Picasso.get()
            .load(movie.getPosterUrl(MovieModel.PosterSize.W342))
            .into(iv_movie_info_poster)
//            .into(iv_movie_info_poster, object : com.squareup.picasso.Callback {
//                override fun onError() {
//                    activity.supportStartPostponedEnterTransition()
//                }
//                override fun onSuccess() {
//                    activity.supportStartPostponedEnterTransition()
//                }
//            })
    }

    companion object {
        private const val ARG_MOVIE = "movie"

        @JvmStatic
        fun newInstance(movie: MovieModel) = MovieInfoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
        }
    }
}
