package com.nex3z.popularmoviekotlin.detail.video

import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.base.BasePresenter
import com.nex3z.popularmoviekotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmoviekotlin.domain.interactor.GetVideoUseCase
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel
import com.nex3z.popularmoviekotlin.domain.model.video.VideoModel

class MovieVideoPresenter(val movie: MovieModel) : BasePresenter<MovieVideoView>() {

    private val getVideoUseCase: GetVideoUseCase = App.service.create(::GetVideoUseCase)
    private val videos: MutableList<VideoModel> = mutableListOf()

    override fun destroy() {
        getVideoUseCase.dispose()
        super.destroy()
    }

    fun init() {
        fetchVideos()
    }

    fun onVideoClick(position: Int) {
        if (position >= 0 && position < videos.size) {
            view?.playVideo(videos[position])
        }
    }

    fun fetchVideos() {
        getVideoUseCase.execute(VideoObserver(), GetVideoUseCase.Params.forMovie(movie.id))
    }

    private inner class VideoObserver : DefaultObserver<List<VideoModel>>() {
        override fun onStart() {
            super.onStart()
            view?.showLoading()
        }

        override fun onNext(data: List<VideoModel>) {
            super.onNext(data)
            view?.hideLoading()
            view?.renderVideos(data)
        }

        override fun onError(throwable: Throwable) {
            super.onError(throwable)
            view?.hideLoading()
            view?.showError(throwable.message)
        }
    }

}