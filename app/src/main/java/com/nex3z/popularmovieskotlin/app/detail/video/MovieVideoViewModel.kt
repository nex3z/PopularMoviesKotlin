package com.nex3z.popularmovieskotlin.app.detail.video

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nex3z.popularmovieskotlin.domain.PopMovieService
import com.nex3z.popularmovieskotlin.domain.interactor.DefaultObserver
import com.nex3z.popularmovieskotlin.domain.interactor.video.GetVideoUseCase
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel

class MovieVideoViewModel(
    application: Application,
    service: PopMovieService
) : AndroidViewModel(application) {
    private val getVideoUseCase: GetVideoUseCase = service.create(::GetVideoUseCase)
    private val _videos = MutableLiveData<List<VideoModel>>()

    lateinit var movie: MovieModel

    val videos: LiveData<List<VideoModel>> = _videos

    fun fetchVideos() {
        getVideoUseCase.execute(VideoObserver(), GetVideoUseCase.Params.forMovie(movie.id))
    }

    private inner class VideoObserver : DefaultObserver<List<VideoModel>>() {
        override fun onNext(data: List<VideoModel>) {
            _videos.value = data
        }
    }

}