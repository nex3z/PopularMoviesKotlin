package com.nex3z.popularmoviekotlin.domain.model.movie

import android.os.Parcel
import android.os.Parcelable

data class MovieModel(
        val posterPath: String = "",
        val adult: Boolean = false,
        val overview: String = "",
        val releaseDate: String = "",
        val genreIds: List<Int> = emptyList(),
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
    sealed class PosterSize(val value: String) {
        class W92() : PosterSize("w92")
        class W154() : PosterSize("w154")
        class W185() : PosterSize("w185")
        class W342() : PosterSize("w342")
        class W500() : PosterSize("w500")
        class W780() : PosterSize("w780")
        class Original() : PosterSize("original")

        companion object {
            val W92 = W92()
            val W154 = W154()
            val W185 = W185()
            val W342 = W342()
            val W500 = W500()
            val W780 = W780()
            val ORIGINAL = Original()
        }
    }

    sealed class BackdropSize(val value: String) {
        class W300() : BackdropSize("w300")
        class W780() : BackdropSize("w780")
        class W1280() : BackdropSize("w1280")
        class Original() : BackdropSize("original")

        companion object {
            val W300 = W300()
            val W780 = W780()
            val W1280 = W1280()
            val ORIGINAL = Original()
        }
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
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
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

