package com.nex3z.popularmoviekotlin.detail.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.base.BaseFragment
import com.nex3z.popularmoviekotlin.base.HasPresenter
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.util.GenreUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_info.*

class MovieInfoFragment : BaseFragment(), MovieInfoView, HasPresenter<MovieInfoPresenter> {

    private lateinit var movie: MovieModel
    private lateinit var presenter: MovieInfoPresenter

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

    override fun getPresenter(): MovieInfoPresenter = presenter

    override fun renderMovie(movie: MovieModel) {
        tv_item_movie_vote_average.text = movie.voteAverage.toString()
        tv_item_movie_release_date.text = movie.releaseDate
        tv_item_movie_genre.text = GenreUtil.getGenre(context, movie.genreIds)
        tv_movie_info_overview.text = movie.overview
        Picasso.with(context)
                .load(movie.getPosterUrl(MovieModel.PosterSize.W342))
                .into(iv_movie_info_poster)
    }

    private fun init() {
        presenter = MovieInfoPresenter(movie)
        presenter.view = this
        presenter.init()
    }

    companion object {
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
