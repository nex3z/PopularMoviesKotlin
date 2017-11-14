package com.nex3z.popularmoviekotlin.domain

import com.nex3z.popularmoviekotlin.data.repository.MovieRepository
import com.nex3z.popularmoviekotlin.domain.exception.CreateUseCaseFailedException
import com.nex3z.popularmoviekotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmoviekotlin.domain.executor.ThreadExecutor
import com.nex3z.popularmoviekotlin.domain.interactor.BaseUseCase
import kotlin.reflect.KClass
import kotlin.reflect.full.createType
import kotlin.reflect.full.primaryConstructor

class PopMovieService(movieRepository: MovieRepository,
                      threadExecutor: ThreadExecutor,
                      postExecutionThread: PostExecutionThread
) : Context(movieRepository, postExecutionThread, threadExecutor), UseCaseFactory {

    override fun <T : BaseUseCase<*, *>> create(kClass: KClass<T>): T {
        val constructor = kClass.primaryConstructor
        if (constructor != null && constructor.parameters.size == 1
                && constructor.parameters[0].type == Context::class.createType()) {
            return constructor.call(this);
        } else {
            throw CreateUseCaseFailedException("Failed to find proper constructor to create use case");
        }
    }

    companion object {
        private val LOG_TAG = PopMovieService::class.java.simpleName
    }

}
