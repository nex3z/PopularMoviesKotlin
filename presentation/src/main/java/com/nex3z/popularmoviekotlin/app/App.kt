package com.nex3z.popularmoviekotlin.app

import android.app.Application
import android.content.Context
import com.nex3z.popularmoviekotlin.BuildConfig
import com.nex3z.popularmoviekotlin.UiThread
import com.nex3z.popularmoviekotlin.data.net.RestClient
import com.nex3z.popularmoviekotlin.data.repository.MovieRepositoryImpl
import com.nex3z.popularmoviekotlin.domain.PopMovieService
import com.nex3z.popularmoviekotlin.domain.executor.JobExecutor

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        initService()
    }

    private fun initService() {
        val restClient = RestClient(BuildConfig.API_KEY)
        val repository = MovieRepositoryImpl(this, restClient)
        service = PopMovieService(repository, JobExecutor(), UiThread)
    }

    companion object {
        lateinit var appContext: Context
            private set

        lateinit var service: PopMovieService
    }
}