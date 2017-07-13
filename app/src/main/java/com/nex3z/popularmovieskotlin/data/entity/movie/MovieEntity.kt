package com.nex3z.popularmovieskotlin.data.entity.movie

import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class MovieEntity(
        var poster_path: String = "",
        var adult: Boolean = false,
        var overview: String = "",
        var release_date: String = "",
        @field:Ignore var genre_ids: List<Int> = emptyList(),
        @field:PrimaryKey var id: Long = 0,
        var original_title: String = "",
        var original_language: String = "",
        var title: String = "",
        var backdrop_path: String = "",
        var popularity: Double = 0.0,
        var vote_count: Int = 0,
        var video: Boolean = false,
        var vote_average: Double = 0.0
) : RealmObject()
