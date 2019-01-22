package com.nex3z.popularmovieskotlin.data.entity.discover

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movies")
data class MovieEntity(
    var vote_count: Int = 0,
    @field:PrimaryKey var id: Long = 0,
    var video: Boolean = false,
    var vote_average: Double = 0.0,
    var title: String = "",
    var popularity: Double = 0.0,
    var poster_path: String = "",
    var original_language: String = "",
    var original_title: String = "",
    var genre_ids: List<Int> = emptyList(),
    var backdrop_path: String? = null,
    var adult: Boolean = false,
    var overview: String = "",
    var release_date: String = ""
)
