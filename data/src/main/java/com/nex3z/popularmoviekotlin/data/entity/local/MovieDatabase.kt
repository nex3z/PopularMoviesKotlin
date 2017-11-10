package com.nex3z.popularmoviekotlin.data.entity.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity

@Database(entities = arrayOf(MovieEntity::class), version = 1)
@TypeConverters(Converter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
