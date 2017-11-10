package com.nex3z.popularmoviekotlin.data.entity.local

import android.arch.persistence.room.*
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Single<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId LIMIT 1")
    fun getMovieById(movieId: Long): Maybe<MovieEntity>

    @Query("SELECT COUNT(*) FROM movies WHERE id = :movieId LIMIT 1")
    fun checkMovieById(movieId: Long): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)

}