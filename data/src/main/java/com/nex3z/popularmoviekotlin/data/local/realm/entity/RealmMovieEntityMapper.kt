package com.nex3z.popularmoviekotlin.data.local.realm.entity

import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity
import io.realm.RealmList

fun RealmMovieEntity.transform(): MovieEntity {
    val genreIds = mutableListOf(this.genre_ids.size)
    genreIds.addAll(this.genre_ids)
    return MovieEntity(
            poster_path = this.poster_path,
            adult = this.adult,
            overview = this.overview,
            release_date = this.release_date,
            genre_ids = genreIds,
            id = this.id,
            original_title = this.original_title,
            original_language = this.original_language,
            title = this.title,
            backdrop_path = this.backdrop_path,
            popularity = this.popularity,
            vote_count = this.vote_count,
            video = this.video,
            vote_average = this.vote_average
    )
}

fun MovieEntity.transform(): RealmMovieEntity {
    val genreIds = RealmList<Int>()
    genreIds.addAll(this.genre_ids)
    return RealmMovieEntity(
            poster_path = this.poster_path,
            adult = this.adult,
            overview = this.overview,
            release_date = this.release_date,
            genre_ids = genreIds,
            id = this.id,
            original_title = this.original_title,
            original_language = this.original_language,
            title = this.title,
            backdrop_path = this.backdrop_path,
            popularity = this.popularity,
            vote_count = this.vote_count,
            video = this.video,
            vote_average = this.vote_average
    )
}