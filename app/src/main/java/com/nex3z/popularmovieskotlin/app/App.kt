package com.nex3z.popularmovieskotlin.app

import com.nex3z.popularmovieskotlin.data.net.RestClient
import android.app.Application
import android.content.Context

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        restClient = RestClient()
    }

    companion object {
        lateinit var appContext: Context
            private set
        lateinit var restClient: RestClient
            private set
    }
}
