package com.nex3z.popularmovieskotlin.domain.model.movie

import android.os.Parcel
import android.os.Parcelable

data class MovieModel (
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

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(posterPath)
        dest.writeInt((if (adult) 1 else 0))
        dest.writeString(overview)
        dest.writeString(releaseDate)
        dest.writeList(genreIds)
        dest.writeLong(id)
        dest.writeString(originalTitle)
        dest.writeString(originalLanguage)
        dest.writeString(title)
        dest.writeString(backdropPath)
        dest.writeDouble(popularity)
        dest.writeInt(voteCount)
        dest.writeInt((if (video) 1 else 0))
        dest.writeDouble(voteAverage)
        dest.writeInt((if (favourite) 1 else 0))
    }

    fun getPosterImageUrl(size: String = "w185"): String {
        return BASE_IMAGE_URL + size + "/" + posterPath
    }

    fun getBackdropImageUrl(size: String = "w780"): String {
        return BASE_IMAGE_URL + size + "/" + backdropPath
    }

    companion object {
        val BASE_IMAGE_URL = "http://image.tmdb.org/t/p/"

        @JvmField val CREATOR: Parcelable.Creator<MovieModel> =
                object : Parcelable.Creator<MovieModel> {
                    override fun createFromParcel(source: Parcel): MovieModel = MovieModel(source)
                    override fun newArray(size: Int): Array<MovieModel?> = arrayOfNulls(size)
                }
    }

}
