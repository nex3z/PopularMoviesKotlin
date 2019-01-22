package com.nex3z.popularmovieskotlin.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread {

    val scheduler: Scheduler

}
