package com.nex3z.popularmovieskotlin.domain

import com.nex3z.popularmovieskotlin.data.local.MovieDaoFactory
import com.nex3z.popularmovieskotlin.data.net.RestClient
import com.nex3z.popularmovieskotlin.data.repository.MovieRepository
import com.nex3z.popularmovieskotlin.data.repository.MovieRepositoryImpl
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase

class PopMovieService(
    appContext: android.content.Context,
    config: Config,
    override val threadExecutor: ThreadExecutor,
    override val postExecutionThread: PostExecutionThread
) : Context, UseCaseFactory {

    override val movieRepository: MovieRepository

    init {
        val restClient = RestClient(config.apiKey)
        this.movieRepository = MovieRepositoryImpl(
            MovieDaoFactory.createRoomMovieDao(appContext), restClient)
    }

    override fun <T : BaseUseCase<*, *>> create(factory: (Context) -> T): T = factory(this)

}
