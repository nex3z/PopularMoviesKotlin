package com.nex3z.popularmovieskotlin.domain

import com.nex3z.popularmovieskotlin.data.repository.MovieRepository
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import com.nex3z.popularmovieskotlin.domain.executor.ThreadExecutor

interface Context {
    val movieRepository: MovieRepository
    val postExecutionThread: PostExecutionThread
    val threadExecutor: ThreadExecutor
}
