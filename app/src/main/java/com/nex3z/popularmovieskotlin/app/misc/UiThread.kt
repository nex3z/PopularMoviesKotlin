package com.nex3z.popularmovieskotlin.app.misc

import com.nex3z.popularmovieskotlin.domain.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object UiThread : PostExecutionThread {

    override val scheduler: Scheduler = AndroidSchedulers.mainThread()

}