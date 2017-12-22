package com.nex3z.popularmoviekotlin.data.local.realm.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmMovieEntity(
        var poster_path: String = "",
        var adult: Boolean = false,
        var overview: String = "",
        var release_date: String = "",
        var genre_ids: RealmList<Int> = RealmList(),
        @field:PrimaryKey var id: Long = 0,
        var original_title: String = "",
        var original_language: String = "",
        var title: String = "",
        var backdrop_path: String? = null,
        var popularity: Double = 0.0,
        var vote_count: Int = 0,
        var video: Boolean = false,
        var vote_average: Double = 0.0
) : RealmObject()
