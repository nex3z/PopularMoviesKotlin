package com.nex3z.popularmovieskotlin.presentation.ui.detail.info

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_movie_info.*

class MovieInfoFragment : Fragment() {

    private lateinit var movie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movie = arguments.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        renderMovie()
    }

    private fun renderMovie() {
        Log.v(LOG_TAG, "renderMovie(): movie = " + movie)
        tv_movie_title.text = movie.title
        tv_release_date.text = movie.releaseDate
        tv_vote.text = String.format(getString(R.string.vote_average), movie.voteAverage)
        tv_genre.text = getGenre(context, movie.genreIds)
        tv_overview.text = movie.overview

        Picasso.with(context)
                .load(movie.getPosterImageUrl())
                .error(R.drawable.placeholder_poster_white)
                .placeholder(R.drawable.placeholder_poster_white)
                .into(iv_poster, object : com.squareup.picasso.Callback {
                    override fun onError() {
                        activity.supportStartPostponedEnterTransition()
                    }
                    override fun onSuccess() {
                        activity.supportStartPostponedEnterTransition()
                    }
                })
    }

    fun getGenre(context: Context, genres: List<Int>?): String {
        if (genres == null) {
            return ""
        }
        val sb = StringBuilder()
        for (i in genres.indices) {
            sb.append(getGenreName(context, genres[i]))
            if (i != genres.size - 1) {
                sb.append(", ")
            }
        }
        return sb.toString()
    }

    fun getGenreName(context: Context, code: Int): String {
        when (code) {
            28 -> return context.getString(R.string.genre_action)
            12 -> return context.getString(R.string.genre_adventure)
            16 -> return context.getString(R.string.genre_animation)
            35 -> return context.getString(R.string.genre_comedy)
            80 -> return context.getString(R.string.genre_crime)
            99 -> return context.getString(R.string.genre_documentary)
            18 -> return context.getString(R.string.genre_drama)
            10751 -> return context.getString(R.string.genre_family)
            14 -> return context.getString(R.string.genre_fantasy)
            36 -> return context.getString(R.string.genre_history)
            27 -> return context.getString(R.string.genre_horror)
            10402 -> return context.getString(R.string.genre_music)
            9648 -> return context.getString(R.string.genre_mystery)
            10749 -> return context.getString(R.string.genre_romance)
            878 -> return context.getString(R.string.genre_science_fiction)
            10770 -> return context.getString(R.string.genre_tv_movie)
            53 -> return context.getString(R.string.genre_thriller)
            10752 -> return context.getString(R.string.genre_war)
            37 -> return context.getString(R.string.genre_western)
            else -> return ""
        }
    }

    companion object {
        private val LOG_TAG = "MovieInfoFragment"
        private val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: MovieModel): MovieInfoFragment {
            val fragment = MovieInfoFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            fragment.arguments = args
            return fragment
        }
    }

}
