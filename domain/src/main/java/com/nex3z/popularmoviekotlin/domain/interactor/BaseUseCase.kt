package com.nex3z.popularmoviekotlin.domain.interactor

import com.nex3z.popularmoviekotlin.domain.Context

abstract class BaseUseCase<T, Params>(protected val context: Context)
    : UseCase<T, Params>(context.threadExecutor, context.postExecutionThread)
