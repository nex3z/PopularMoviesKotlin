package com.nex3z.popularmoviekotlin.domain

import com.nex3z.popularmoviekotlin.data.repository.MovieRepository
import com.nex3z.popularmoviekotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmoviekotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmoviekotlin.domain.interactor.BaseUseCase

class PopMovieService(movieRepository: MovieRepository,
                      threadExecutor: ThreadExecutor,
                      postExecutionThread: PostExecutionThread
) : Context(movieRepository, postExecutionThread, threadExecutor), UseCaseFactory {

    override fun <T : BaseUseCase<*, *>> create(factory: (Context) -> T): T = factory(this)

}
