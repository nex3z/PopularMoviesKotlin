package com.nex3z.popularmovieskotlin.data.local

import android.util.Log
import com.nex3z.popularmovieskotlin.data.entity.movie.MovieEntity
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class LocalMovieDataStoreImpl : LocalMovieDataStore {

    override fun getFavourites(): Single<List<MovieEntity>> {
        return Single.create() {
            val realm = buildDefaultRealm()
            val result = realm
                    .where(MovieEntity::class.java)
                    .findAll()
            it.onSuccess(realm.copyFromRealm(result))
            realm.close()
        }
    }

    override fun addFavourite(movie: MovieEntity): Single<Unit> {
        return Single.create() {
            val realm = buildDefaultRealm()
            try {
                realm.beginTransaction()
                realm.copyToRealm(movie)
                realm.commitTransaction()
                it.onSuccess(Unit)
            } catch (exception: RealmPrimaryKeyConstraintException) {
                it.onError(RuntimeException("The movie is already set to favourite."))
            } finally {
                realm.close()
            }
        }
    }

    override fun removeFavourite(movieId: Long): Single<MovieEntity> {
        return Single.create() {
            val realm = buildDefaultRealm()
            val result = realm.where(MovieEntity::class.java)
                    .equalTo("id", movieId)
                    .findAll()
            Log.v(LOG_TAG, "isFavourite(): movieId = " + movieId
                    + ", result size = " + result.size)
            if (result.size > 0) {
                val entity = realm.copyFromRealm(result.first())
                realm.beginTransaction()
                result.deleteAllFromRealm()
                realm.commitTransaction()
                it.onSuccess(entity)
            } else {
                it.onError(RuntimeException("The movie is not set to favourite."))
            }
            realm.close()
        }
    }

    override fun isFavourite(movieId: Long): Single<Boolean> {
        return Single.create() {
            val realm = buildDefaultRealm()
            val result = realm.where(MovieEntity::class.java)
                    .equalTo("id", movieId)
                    .findAll()
            Log.v(LOG_TAG, "isFavourite(): movieId = " + movieId
                    + ", result size = " + result.size)
            if (result.size > 0) {
                it.onSuccess(true)
            } else {
                it.onSuccess(false)
            }
            realm.close()
        }
    }

    private fun buildDefaultRealm(): Realm {
        val configuration = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build()
        return Realm.getInstance(configuration)
    }

    companion object {
        val LOG_TAG = "LocalMovieDataStoreImpl"
    }
}