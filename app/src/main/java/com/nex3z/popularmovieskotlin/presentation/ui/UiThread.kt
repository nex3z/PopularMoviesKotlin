package com.nex3z.popularmovieskotlin.presentation.ui

import io.reactivex.android.schedulers.AndroidSchedulers
import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import io.reactivex.Scheduler

object UiThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}
