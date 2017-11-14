package com.nex3z.popularmoviekotlin.domain

import com.nex3z.popularmoviekotlin.data.repository.MovieRepository
import com.nex3z.popularmoviekotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmoviekotlin.domain.executor.ThreadExecutor

abstract class Context(val movieRepository: MovieRepository,
                       val postExecutionThread: PostExecutionThread,
                       val threadExecutor: ThreadExecutor)
