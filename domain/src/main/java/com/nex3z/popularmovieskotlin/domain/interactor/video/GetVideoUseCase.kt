package com.nex3z.popularmovieskotlin.domain.interactor.video

import com.nex3z.popularmovieskotlin.data.entity.video.GetMovieVideosResp
import com.nex3z.popularmovieskotlin.domain.Context
import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import com.nex3z.popularmovieskotlin.domain.model.video.transform
import io.reactivex.Observable

class GetVideoUseCase(context: Context)
    : BaseUseCase<List<VideoModel>, GetVideoUseCase.Params>(context) {

    override fun buildUseCaseObservable(params: Params): Observable<List<VideoModel>> {
        return context.movieRepository
            .getVideos(params.movieId)
            .map(GetMovieVideosResp::transform)
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
