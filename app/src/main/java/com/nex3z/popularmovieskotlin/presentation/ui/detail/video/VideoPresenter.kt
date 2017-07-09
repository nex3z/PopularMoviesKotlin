package com.nex3z.popularmovieskotlin.presentation.ui.detail.video

import android.net.Uri
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.video.GetVideosUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BasePresenter

class VideoPresenter(
        private val getVideosUseCase: GetVideosUseCase
) : BasePresenter<VideoView>() {

    private lateinit var movie: MovieModel
    private var videos: List<VideoModel> = emptyList()

    override fun destroy() {
        super.destroy()
        getVideosUseCase.dispose()
    }

    fun init(movie: MovieModel) {
        this.movie = movie
        fetchVideo()
    }

    fun onVideoClick(position: Int) {
        val video = videos[position]
        view?.playVideo(Uri.parse(YOUTUBE_BASE_URL + video.key))
    }

    private fun fetchVideo() {
        getVideosUseCase.execute(GetVideosObserver(), GetVideosUseCase.Params.forMovie(movie.id))
    }

    private inner class GetVideosObserver : DefaultObserver<List<VideoModel>>() {
        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<VideoModel>) {
            super.onNext(data)
            videos = data
            view?.hideLoading()
            view?.renderVideos(videos)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message ?: "")
            throwable.printStackTrace()
        }
    }

    companion object {
        private val LOG_TAG = "VideoPresenter"
        private val YOUTUBE_BASE_URL = "http://www.youtube.com/watch?v="
    }
}