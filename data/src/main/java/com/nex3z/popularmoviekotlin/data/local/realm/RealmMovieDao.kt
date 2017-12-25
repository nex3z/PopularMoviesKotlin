package com.nex3z.popularmoviekotlin.data.local.realm

import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.data.local.realm.entity.RealmMovieEntity
import com.nex3z.popularmoviekotlin.data.local.realm.entity.transform
import com.nex3z.popularmoviekotlin.data.local.room.MovieDao
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmMovieDao : MovieDao {

    private val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

    override fun getMovies(): Single<List<MovieEntity>> {
        return Single.create { emitter ->
            val realm = getRealm()
            val result = realm
                    .where(RealmMovieEntity::class.java)
                    .findAll()
            emitter.onSuccess(result.map { it.transform() })
            realm.close()
        }
    }

    override fun getMovieById(movieId: Long): Maybe<MovieEntity> {
        return Maybe.create{ emitter ->
            val realm = getRealm()
            val entity = realm
                    .where(RealmMovieEntity::class.java)
                    .equalTo("id", movieId)
                    .findFirst()
            if (entity != null) {
                emitter.onSuccess(entity.transform())
            }
            emitter.onComplete()
            realm.close()
        }
    }

    override fun checkMovieById(movieId: Long): Single<Int> {
        return Single.create{ emitter ->
            val realm = getRealm()
            val entity = realm
                    .where(RealmMovieEntity::class.java)
                    .equalTo("id", movieId)
                    .findFirst()
            if (entity == null) {
                emitter.onSuccess(0)
            } else {
                emitter.onSuccess(1)
            }
            realm.close()
        }
    }

    override fun insert(movie: MovieEntity) {
        val realm = getRealm()
        realm.executeTransaction {
            realm.copyToRealmOrUpdate(movie.transform())
        }
        realm.close()
    }

    override fun delete(movie: MovieEntity) {
        val realm = getRealm()
        val result = realm
                .where(RealmMovieEntity::class.java)
                .equalTo("id", movie.id)
                .findAll()
        realm.executeTransaction {
            result.deleteAllFromRealm()
        }
        realm.close()
    }

    private fun getRealm(): Realm {
        return Realm.getInstance(config)
    }

}