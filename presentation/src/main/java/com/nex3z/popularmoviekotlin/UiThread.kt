package com.nex3z.popularmoviekotlin

import com.nex3z.popularmoviekotlin.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object UiThread : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()

}