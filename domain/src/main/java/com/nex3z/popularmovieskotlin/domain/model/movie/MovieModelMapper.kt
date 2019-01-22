package com.nex3z.popularmovieskotlin.domain.model.movie

import com.nex3z.popularmovieskotlin.data.entity.discover.DiscoverMovieResp
import com.nex3z.popularmovieskotlin.data.entity.discover.MovieEntity

fun DiscoverMovieResp.transform(): List<MovieModel>
        = this.results.map { it.transform() }

fun MovieEntity.transform(): MovieModel = this.transform(false)

fun MovieEntity.transform(favourite: Boolean = false): MovieModel {
    return MovieModel(
        voteCount = this.vote_count,
        id = this.id,
        video = this.video,
        voteAverage = this.vote_average,
        title = this.title,
        popularity = this.popularity,
        posterPath = this.poster_path,
        originalLanguage = this.original_language,
        originalTitle = this.original_title,
        genreIds = toGenre(this.genre_ids),
        backdropPath = this.backdrop_path,
        adult = this.adult,
        overview = this.overview,
        releaseDate = this.release_date,
        favourite = favourite
    )
}

fun MovieModel.transform(): MovieEntity {
    return MovieEntity(
        vote_count = this.voteCount,
        id = this.id,
        video = this.video,
        vote_average = this.voteAverage,
        title = this.title,
        popularity = this.popularity,
        poster_path = this.posterPath,
        original_language = this.originalLanguage,
        original_title = this.originalTitle,
        genre_ids = fromGenre(this.genreIds),
        backdrop_path = this.backdropPath,
        adult = this.adult,
        overview = this.overview,
        release_date = this.releaseDate
    )
}

fun toGenre(genres: List<Int>?): List<MovieModel.Genre> =
    genres?.map { MovieModel.Genre.fromValue(it, MovieModel.Genre.UNKNOWN) } ?: emptyList()

fun fromGenre(genres: List<MovieModel.Genre>?): List<Int> =
    genres?.map(MovieModel.Genre::value) ?: emptyList()
