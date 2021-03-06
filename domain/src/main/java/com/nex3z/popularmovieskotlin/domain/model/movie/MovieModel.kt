package com.nex3z.popularmovieskotlin.domain.model.movie

import android.os.Parcel
import android.os.Parcelable
import com.nex3z.popularmovieskotlin.domain.misc.EnumConverter
import com.nex3z.popularmovieskotlin.domain.misc.HasValue
import com.nex3z.popularmovieskotlin.domain.misc.buildValueMap

data class MovieModel(
    val voteCount: Int = 0,
    val id: Long = 0,
    val video: Boolean = false,
    val voteAverage: Double = 0.0,
    val title: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val originalLanguage: String = "",
    val originalTitle: String = "",
    val genreIds: List<Genre> = emptyList(),
    val backdropPath: String? = null,
    val adult: Boolean = false,
    val overview: String = "",
    val releaseDate: String = "",
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

    fun getBackdropUrl(size: BackdropSize): String? {
        return if (backdropPath != null) BASE_URL + size.value + "/" + backdropPath else null
    }

    constructor(source: Parcel) : this(
        source.readInt(),
        source.readLong(),
        1 == source.readInt(),
        source.readDouble(),
        source.readString() ?: "",
        source.readDouble(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        ArrayList<Genre>().apply { source.readList(this, Genre::class.java.classLoader) },
        source.readString(),
        1 == source.readInt(),
        source.readString() ?: "",
        source.readString() ?: "",
        1 == source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(voteCount)
        writeLong(id)
        writeInt((if (video) 1 else 0))
        writeDouble(voteAverage)
        writeString(title)
        writeDouble(popularity)
        writeString(posterPath)
        writeString(originalLanguage)
        writeString(originalTitle)
        writeList(genreIds)
        writeString(backdropPath)
        writeInt((if (adult) 1 else 0))
        writeString(overview)
        writeString(releaseDate)
        writeInt((if (favourite) 1 else 0))
    }

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/"

        @JvmField  @Suppress("unused")
        val CREATOR: Parcelable.Creator<MovieModel> = object : Parcelable.Creator<MovieModel> {
            override fun createFromParcel(source: Parcel): MovieModel = MovieModel(source)
            override fun newArray(size: Int): Array<MovieModel?> = arrayOfNulls(size)
        }
    }
}