package com.nex3z.popularmoviekotlin.util

import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.app.App
import com.nex3z.popularmoviekotlin.domain.model.movie.MovieModel

fun MovieModel.Genre.getName(): String? {
    val id : Int? = when (this.value) {
        28 -> R.string.caption_genre_action
        12 -> R.string.caption_genre_adventure
        16 -> R.string.caption_genre_animation
        35 -> R.string.caption_genre_comedy
        80 -> R.string.caption_genre_crime
        99 -> R.string.caption_genre_documentary
        18 -> R.string.caption_genre_drama
        10751 -> R.string.caption_genre_family
        14 -> R.string.caption_genre_fantasy
        36 -> R.string.caption_genre_history
        27 -> R.string.caption_genre_horror
        10402 -> R.string.caption_genre_music
        9648 -> R.string.caption_genre_mystery
        10749 -> R.string.caption_genre_romance
        878 -> R.string.caption_genre_science_fiction
        10770 -> R.string.caption_genre_tv_movie
        53 -> R.string.caption_genre_thriller
        10752 -> R.string.caption_genre_war
        37 -> R.string.caption_genre_western
        else -> null
    }
    return if (id != null) App.appContext.getString(id) else null
}

fun List<MovieModel.Genre>.getNames(): String =
        this.mapNotNull { it.getName() }.joinToString(", ")
