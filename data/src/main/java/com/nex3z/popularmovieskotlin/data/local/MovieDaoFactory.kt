package com.nex3z.popularmovieskotlin.data.local

import android.content.Context
import androidx.room.Room

object MovieDaoFactory {

    private lateinit var movieDatabase: MovieDatabase

    fun createRoomMovieDao(context: Context): MovieDao {
        if (!::movieDatabase.isInitialized) {
            movieDatabase = Room
                .databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "PopularMovies.db")
                .build()
        }
        return movieDatabase.movieDao()
    }

}
