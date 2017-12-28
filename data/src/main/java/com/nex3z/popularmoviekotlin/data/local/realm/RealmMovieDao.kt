package com.nex3z.popularmoviekotlin.data.local.realm

import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import com.nex3z.popularmoviekotlin.data.local.realm.entity.RealmMovieEntity
import com.nex3z.popularmoviekotlin.data.local.realm.entity.transform
import com.nex3z.popularmoviekotlin.data.local.room.MovieDao
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration

class RealmMovieDao : BaseRealmDao(), MovieDao {

    override val config: RealmConfiguration =
            RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build()

    override fun getMovies(): Single<List<MovieEntity>> =
            queryAllInternal(RealmMovieEntity::class)
                    .map { it.map { it.transform() } }

    override fun getMovieById(movieId: Long): Maybe<MovieEntity> =
            queryByInternal(RealmMovieEntity::class, "id", movieId)
                    .map { it.transform() }

    override fun insert(movie: MovieEntity) = insertInternal(movie.transform())

    override fun delete(movie: MovieEntity) =
            deleteInternal(RealmMovieEntity::class, "id", movie.id)

    private fun getRealm(): Realm {
        return Realm.getInstance(config)
    }

}