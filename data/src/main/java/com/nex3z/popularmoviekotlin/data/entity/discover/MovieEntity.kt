package com.nex3z.popularmoviekotlin.data.entity.discover

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
        var poster_path: String = "",
        var adult: Boolean = false,
        var overview: String = "",
        var release_date: String = "",
        var genre_ids: List<Int> = emptyList(),
        @field:PrimaryKey var id: Long = 0,
        var original_title: String = "",
        var original_language: String = "",
        var title: String = "",
        var backdrop_path: String? = null,
        var popularity: Double = 0.0,
        var vote_count: Int = 0,
        var video: Boolean = false,
        var vote_average: Double = 0.0
)
