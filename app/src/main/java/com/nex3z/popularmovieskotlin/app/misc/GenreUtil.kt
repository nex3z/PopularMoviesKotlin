package com.nex3z.popularmovieskotlin.app.misc

import com.nex3z.popularmovieskotlin.app.App
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel


fun MovieModel.Genre.getName(): String? {
    val id : Int = when (this) {
        MovieModel.Genre.ACTION -> R.string.caption_genre_action
        MovieModel.Genre.ADVENTURE -> R.string.caption_genre_adventure
        MovieModel.Genre.ANIMATION -> R.string.caption_genre_animation
        MovieModel.Genre.COMEDY -> R.string.caption_genre_comedy
        MovieModel.Genre.CRIME -> R.string.caption_genre_crime
        MovieModel.Genre.DOCUMENTARY -> R.string.caption_genre_documentary
        MovieModel.Genre.DRAMA -> R.string.caption_genre_drama
        MovieModel.Genre.FAMILY -> R.string.caption_genre_family
        MovieModel.Genre.FANTASY -> R.string.caption_genre_fantasy
        MovieModel.Genre.HISTORY -> R.string.caption_genre_history
        MovieModel.Genre.HORROR -> R.string.caption_genre_horror
        MovieModel.Genre.MUSIC -> R.string.caption_genre_music
        MovieModel.Genre.MYSTERY -> R.string.caption_genre_mystery
        MovieModel.Genre.ROMANCE -> R.string.caption_genre_romance
        MovieModel.Genre.SCIENCE_FICTION -> R.string.caption_genre_science_fiction
        MovieModel.Genre.TV_MOVIE -> R.string.caption_genre_tv_movie
        MovieModel.Genre.THRILLER -> R.string.caption_genre_thriller
        MovieModel.Genre.WAR -> R.string.caption_genre_war
        MovieModel.Genre.WESTERN -> R.string.caption_genre_western
        else -> -1
    }
    return if (id != -1) App.appContext.getString(id) else null
}

fun List<MovieModel.Genre>.getNames(): String =
    this.mapNotNull { it.getName() }.joinToString(", ")
