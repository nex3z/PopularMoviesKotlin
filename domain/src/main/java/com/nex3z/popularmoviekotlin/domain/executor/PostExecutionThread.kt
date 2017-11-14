package com.nex3z.popularmoviekotlin.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler

}
