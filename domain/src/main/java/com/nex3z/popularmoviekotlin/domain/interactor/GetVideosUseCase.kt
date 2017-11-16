package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.domain.Context
import com.nex3z.popularmoviekotlin.domain.model.video.VideoModel
import com.nex3z.popularmoviekotlin.domain.model.video.transform
import io.reactivex.Observable

class GetVideosUseCase(context: Context)
    : BaseUseCase<List<VideoModel>, GetVideosUseCase.Params>(context) {

    override fun buildUseCaseObservable(params: Params): Observable<List<VideoModel>> {
        return context.movieRepository
                .getVideos(params.movieId)
                .map(::transform)
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
