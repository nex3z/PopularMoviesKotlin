package com.nex3z.popularmoviekotlin.domain.model.movie

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity

fun DiscoverMovieResponse.transform(): List<MovieModel>
        = this.results.map { it.transform() }

fun MovieEntity.transform(): MovieModel = this.transform(false)

fun MovieEntity.transform(favourite: Boolean = false): MovieModel {
    return MovieModel(
            posterPath = this.poster_path,
            adult = this.adult,
            overview = this.overview,
            releaseDate = this.release_date,
            genreIds = toGenre(this.genre_ids),
            id = this.id,
            originalTitle = this.original_title,
            originalLanguage = this.original_language,
            title = this.title,
            backdropPath = this.backdrop_path,
            popularity = this.popularity,
            voteCount = this.vote_count,
            video = this.video,
            voteAverage = this.vote_average,
            favourite = favourite
    )
}

fun MovieModel.transform(): MovieEntity {
    return MovieEntity(
            poster_path = this.posterPath,
            adult = this.adult,
            overview = this.overview,
            release_date = this.releaseDate,
            genre_ids = fromGenre(this.genreIds),
            id = this.id,
            original_title = this.originalTitle,
            original_language = this.originalLanguage,
            title = this.title,
            backdrop_path = this.backdropPath,
            popularity = this.popularity,
            vote_count = this.voteCount,
            video = this.video,
            vote_average = this.voteAverage
    )
}

fun toGenre(genres: List<Int>?): List<MovieModel.Genre> =
        genres?.map { MovieModel.Genre.fromValue(it, MovieModel.Genre.UNKNOWN) }
                ?: emptyList()

fun fromGenre(genres: List<MovieModel.Genre>?): List<Int> =
        genres?.map(MovieModel.Genre::value)?: emptyList()
