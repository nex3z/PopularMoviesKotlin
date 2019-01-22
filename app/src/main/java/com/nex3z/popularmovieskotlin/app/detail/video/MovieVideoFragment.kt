package com.nex3z.popularmovieskotlin.app.detail.video

import android.content.Intent
import android.net.Uri
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
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import kotlinx.android.synthetic.main.fragment_movie_video.*

class MovieVideoFragment : Fragment() {

    private lateinit var viewModel: MovieVideoViewModel
    private val adapter: VideoAdapter = VideoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: MovieModel = arguments?.getParcelable(ARG_MOVIE) ?: throw IllegalStateException("movie is missing")
        Log.v(LOG_TAG, "onViewCreated(): movie = $movie")
        viewModel = ViewModelProviders.of(this, ViewModelFactory).get(MovieVideoViewModel::class.java)
        viewModel.movie = movie
        initView()
        bindViewModel()
        viewModel.fetchVideos()
    }

    private fun initView() {
        adapter.onItemClickListener = {
            Log.v(LOG_TAG, "onItemClickListener(): it = $it")
            playVideo(it)
        }

        with(rv_movie_video_list) {
            adapter = this@MovieVideoFragment.adapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun bindViewModel() {
        viewModel.videos.observe(this, OnVideoListChangedObserver())
    }

    fun playVideo(video: VideoModel) {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse(video.videoUrl)
        intent.data = uri
        startActivity(intent)
    }

    inner class OnVideoListChangedObserver : Observer<List<VideoModel>> {
        override fun onChanged(t: List<VideoModel>?) {
            t?.let { adapter.videos = it }
        }
    }

    companion object {
        private val LOG_TAG: String = MovieVideoFragment::class.java.simpleName
        private const val ARG_MOVIE = "movie"

        @JvmStatic
        fun newInstance(movie: MovieModel) = MovieVideoFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
        }
    }
}
