package com.nex3z.popularmoviekotlin.detail.video

import android.content.Intent
import android.net.Uri
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
import com.nex3z.popularmoviekotlin.domain.model.video.VideoModel
import com.nex3z.popularmoviekotlin.misc.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_movie_video.*

class MovieVideoFragment : BaseFragment(), MovieVideoView, HasPresenter<MovieVideoPresenter> {

    private lateinit var movie: MovieModel
    private lateinit var presenter: MovieVideoPresenter
    private val adapter: VideoAdapter = VideoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            movie = arguments.getParcelable(ARG_MOVIE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_video, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun showLoading() {
        pb_movie_video_loading.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_movie_video_loading.visibility = View.GONE
    }

    override fun getPresenter(): MovieVideoPresenter = presenter

    override fun renderVideos(videos: List<VideoModel>) {
        adapter.videos = videos
    }

    override fun playVideo(video: VideoModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse(video.getVideoUrl())
        intent.data = uri
        startActivity(intent)
    }

    private fun init() {
        initView()
        initPresenter()
        presenter.init()
    }

    private fun initView() {
        adapter.onItemClickListener = object: OnItemClickListener {
            override fun onItemClicked(position: Int) {
                presenter.onVideoClick(position)
            }
        }

        with(rv_movie_video_list) {
            adapter = this@MovieVideoFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initPresenter() {
        presenter = MovieVideoPresenter(movie)
        presenter.view = this
    }

    companion object {
        private val ARG_MOVIE = "arg_movie"

        fun newInstance(movie: MovieModel): MovieVideoFragment {
            val fragment = MovieVideoFragment()
            val args = Bundle()
            args.putParcelable(ARG_MOVIE, movie)
            fragment.arguments = args
            return fragment
        }
    }
}
