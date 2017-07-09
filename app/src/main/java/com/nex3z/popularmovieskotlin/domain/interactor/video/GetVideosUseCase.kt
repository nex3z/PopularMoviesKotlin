package com.nex3z.popularmovieskotlin.domain.interactor.video

import com.nex3z.popularmovieskotlin.data.repository.video.VideoRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.UseCase
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModelMapper
import io.reactivex.Observable

class GetVideosUseCase(
        private val videoRepository: VideoRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : UseCase<List<VideoModel>, GetVideosUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Observable<List<VideoModel>> {
        return videoRepository.getVideos(params.movieId)
                .map(VideoModelMapper::transform)
                .toObservable()
    }

    class Params private constructor(val movieId: Long) {
        companion object {
            fun forMovie(movieId: Long): Params {
                return Params(movieId)
            }
        }
    }

}