package com.nex3z.popularmovieskotlin.presentation.ui.detail.video

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.data.repository.video.VideoRepository
import com.nex3z.popularmovieskotlin.data.repository.video.VideoRepositoryImpl
import com.nex3z.popularmovieskotlin.domain.executor.JobExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.video.GetVideosUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import com.nex3z.popularmovieskotlin.presentation.ui.UiThread
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseFragment
import com.nex3z.popularmovieskotlin.presentation.ui.base.HasPresenter
import kotlinx.android.synthetic.main.content_movie_video.*

class MovieVideoFragment : BaseFragment(), VideoView, HasPresenter<VideoPresenter> {

    private lateinit var movie: MovieModel
    private val presenter: VideoPresenter
    private val adapter: VideoAdapter = VideoAdapter()

    init {
        val videoRepo : VideoRepository = VideoRepositoryImpl(App.restClient)
        val useCase = GetVideosUseCase(videoRepo, JobExecutor(), UiThread)
        presenter = VideoPresenter(useCase)
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
        return inflater.inflate(R.layout.fragment_movie_video, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun getPresenter(): VideoPresenter {
        return presenter
    }

    override fun showLoading() {
        pb_load_video.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pb_load_video.visibility = View.GONE
    }

    override fun renderVideos(videos: List<VideoModel>) {
        if (videos.isNotEmpty()) {
            adapter.videos = videos
        } else {
            tv_no_video.visibility = View.VISIBLE
        }
    }

    override fun playVideo(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = uri
        context.startActivity(intent)
    }

    private fun init() {
        initView()
        presenter.init(movie)
    }

    private fun initView() {
        adapter.onItemClickListener = object: VideoAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                presenter.onVideoClick(position)
            }
        }

        rv_video_list.adapter = adapter
        rv_video_list.layoutManager = LinearLayoutManager(context)
        rv_video_list.setHasFixedSize(true)
        rv_video_list.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    }

    companion object {
        private val LOG_TAG = "MovieVideoFragment"
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
