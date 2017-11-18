package com.nex3z.popularmoviekotlin.util

import android.content.Context
import android.util.Log
import com.nex3z.popularmoviekotlin.R

object GenreUtil {
    private val LOG_TAG = GenreUtil::class.java.simpleName

    fun getGenreName(context: Context, code: Int): String {
        when (code) {
            28 -> return context.getString(R.string.caption_genre_action)
            12 -> return context.getString(R.string.caption_genre_adventure)
            16 -> return context.getString(R.string.caption_genre_animation)
            35 -> return context.getString(R.string.caption_genre_comedy)
            80 -> return context.getString(R.string.caption_genre_crime)
            99 -> return context.getString(R.string.caption_genre_documentary)
            18 -> return context.getString(R.string.caption_genre_drama)
            10751 -> return context.getString(R.string.caption_genre_family)
            14 -> return context.getString(R.string.caption_genre_fantasy)
            36 -> return context.getString(R.string.caption_genre_history)
            27 -> return context.getString(R.string.caption_genre_horror)
            10402 -> return context.getString(R.string.caption_genre_music)
            9648 -> return context.getString(R.string.caption_genre_mystery)
            10749 -> return context.getString(R.string.caption_genre_romance)
            878 -> return context.getString(R.string.caption_genre_science_fiction)
            10770 -> return context.getString(R.string.caption_genre_tv_movie)
            53 -> return context.getString(R.string.caption_genre_thriller)
            10752 -> return context.getString(R.string.caption_genre_war)
            37 -> return context.getString(R.string.caption_genre_western)
            else -> {
                Log.w(LOG_TAG, "getGenreName(): Unknown genre: " + code)
                return ""
            }
        }
    }

    fun getGenre(context: Context, genres: List<Int>): String {
        val sb = StringBuilder()
        for (i in genres.indices) {
            sb.append(getGenreName(context, genres[i]))
            if (i != genres.size - 1) {
                sb.append(", ")
            }
        }
        return sb.toString()
    }
}