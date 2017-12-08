package com.nex3z.popularmoviekotlin.data.local

import android.arch.persistence.room.Room
import android.content.Context
import com.nex3z.popularmoviekotlin.data.local.realm.RealmMovieDao
import com.nex3z.popularmoviekotlin.data.local.room.MovieDao
import com.nex3z.popularmoviekotlin.data.local.room.MovieDatabase
import io.realm.Realm

object MovieDaoFactory {

    private lateinit var movieDatabase: MovieDatabase
    private var realmInitialized = false

    fun createRoomMovieDao(context: Context): MovieDao {
        if (!::movieDatabase.isInitialized) {
            movieDatabase = Room
                    .databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "PopularMovie.db")
                    .build()
        }
        return movieDatabase.movieDao()
    }

    fun createRealmMovieDao(context: Context): MovieDao {
        if (!realmInitialized) {
            Realm.init(context)
            realmInitialized = true

        }
        return RealmMovieDao()
    }

}