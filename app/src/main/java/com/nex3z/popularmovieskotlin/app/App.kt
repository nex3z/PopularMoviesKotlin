package com.nex3z.popularmovieskotlin.app

import com.nex3z.popularmovieskotlin.data.net.RestClient
import android.app.Application
import android.content.Context
import io.realm.Realm

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        restClient = RestClient()
        Realm.init(applicationContext)
    }

    companion object {
        lateinit var appContext: Context
            private set
        lateinit var restClient: RestClient
            private set
    }
}
