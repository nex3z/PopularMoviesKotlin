package com.nex3z.popularmoviekotlin.domain.model.movie

import android.os.Parcel
import android.os.Parcelable
import com.nex3z.popularmoviekotlin.domain.misc.EnumConverter
import com.nex3z.popularmoviekotlin.domain.misc.HasValue
import com.nex3z.popularmoviekotlin.domain.misc.buildValueMap

data class MovieModel(
        val posterPath: String = "",
        val adult: Boolean = false,
        val overview: String = "",
        val releaseDate: String = "",
        val genreIds: List<Genre> = emptyList(),
        val id: Long = 0,
        val originalTitle: String = "",
        val originalLanguage: String = "",
        val title: String = "",
        val backdropPath: String = "",
        val popularity: Double = 0.0,
        val voteCount: Int = 0,
        val video: Boolean = false,
        val voteAverage: Double = 0.0,
        var favourite: Boolean = false
) : Parcelable {

    enum class Genre(override val value: Int) : HasValue<Int> {
        ADVENTURE(12),
        FANTASY(14),
        ANIMATION(16),
        DRAMA(18),
        HORROR(27),
        ACTION(28),
        COMEDY(35),
        HISTORY(36),
        WESTERN(37),
        THRILLER(53),
        CRIME(80),
        DOCUMENTARY(99),
        SCIENCE_FICTION(878),
        MYSTERY(9648),
        MUSIC(10402),
        ROMANCE(10749),
        FAMILY(10751),
        WAR(10752),
        TV_MOVIE(10770),
        UNKNOWN(-1);

        companion object : EnumConverter<Int, Genre>(buildValueMap())
    }

    enum class PosterSize(override val value: String) : HasValue<String> {
        W92("w92"),
        W154("w154"),
        W185("w185"),
        W342("w342"),
        W500("w500"),
        W780("w780"),
        Original("original")
    }

    enum class BackdropSize(override val value: String) : HasValue<String> {
        W300("w300"),
        W780("w780"),
        W1280("w1280"),
        Original("original")
    }

    fun getPosterUrl(size: PosterSize): String {
        return BASE_URL + size.value + "/" + posterPath
    }

    fun getBackdropUrl(size: BackdropSize): String {
        return BASE_URL + size.value + "/" + posterPath
    }

    constructor(source: Parcel) : this(
            source.readString(),
            1 == source.readInt(),
            source.readString(),
            source.readString(),
            ArrayList<Genre>().apply { source.readList(this, Genre::class.java.classLoader) },
            source.readLong(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readDouble(),
            source.readInt(),
            1 == source.readInt(),
            source.readDouble(),
            1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(posterPath)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(releaseDate)
        writeList(genreIds)
        writeLong(id)
        writeString(originalTitle)
        writeString(originalLanguage)
        writeString(title)
        writeString(backdropPath)
        writeDouble(popularity)
        writeInt(voteCount)
        writeInt((if (video) 1 else 0))
        writeDouble(voteAverage)
        writeInt((if (favourite) 1 else 0))
    }

    companion object {
        private val BASE_URL = "http://image.tmdb.org/t/p/"

        @JvmField
        val CREATOR: Parcelable.Creator<MovieModel> = object : Parcelable.Creator<MovieModel> {
            override fun createFromParcel(source: Parcel): MovieModel = MovieModel(source)
            override fun newArray(size: Int): Array<MovieModel?> = arrayOfNulls(size)
        }
    }
}

