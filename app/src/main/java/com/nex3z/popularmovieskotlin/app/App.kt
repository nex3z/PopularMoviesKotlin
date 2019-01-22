package com.nex3z.popularmovieskotlin.app

import android.app.Application
import android.content.Context
import com.nex3z.popularmovieskotlin.app.misc.UiThread
import com.nex3z.popularmovieskotlin.domain.Config
import com.nex3z.popularmovieskotlin.domain.PopMovieService
import com.nex3z.popularmovieskotlin.domain.executor.JobExecutor


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        initService()
    }

    private fun initService() {
        val config = Config(BuildConfig.API_KEY)
        service = PopMovieService(this.applicationContext, config, JobExecutor(), UiThread)
    }

    companion object {
        lateinit var appContext: Context
            private set

        lateinit var service: PopMovieService
    }
}