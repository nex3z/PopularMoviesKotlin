package com.nex3z.popularmoviekotlin.data.local.realm

import com.nex3z.popularmoviekotlin.data.local.realm.entity.RealmMovieEntity
import io.reactivex.Maybe
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import kotlin.reflect.KClass

abstract class BaseRealmDao {

    abstract val config: RealmConfiguration

    protected fun getRealmInstance(): Realm = Realm.getInstance(config)

    fun <T: RealmObject> queryAllInternal(clazz: KClass<T>): Single<List<T>> {
        return Single.create { emitter ->
            getRealmInstance().use { realm ->
                val result = realm
                        .where(clazz.java)
                        .findAll()
                val ret = realm.copyFromRealm(result)
                emitter.onSuccess(ret)
            }
        }
    }

    fun <T: RealmObject> queryByInternal(clazz: KClass<T>, field: String, value: Long): Maybe<T> {
        return Maybe.create{ emitter ->
            getRealmInstance().use { realm ->
                val entity = realm
                        .where(clazz.java)
                        .equalTo(field, value)
                        .findFirst()
                if (entity != null) {
                    emitter.onSuccess(realm.copyFromRealm(entity))
                }
                emitter.onComplete()
            }
        }
    }

    fun <T: RealmObject> insertInternal(data: T) {
        getRealmInstance().use { realm ->
            realm.executeTransaction {
                realm.copyToRealmOrUpdate(data)
            }
        }
    }

    fun <T: RealmObject> deleteInternal(clazz: KClass<T>, field: String, value: Long) {
        getRealmInstance().use { realm ->
            val result = realm
                    .where(RealmMovieEntity::class.java)
                    .equalTo(field, value)
                    .findAll()
            realm.executeTransaction {
                result.deleteAllFromRealm()
            }
        }
    }

}