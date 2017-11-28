package com.nex3z.popularmoviekotlin.domain.model.movie

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity

fun transform(response: DiscoverMovieResponse): List<MovieModel>
        = response.results.map{ transform(it) }

fun transform(entities: List<MovieEntity>, favourite: Boolean = false): List<MovieModel>
        = entities.map { transform(it, favourite)}

fun transform(entity: MovieEntity, favourite: Boolean = false): MovieModel {
    return MovieModel(
            posterPath = entity.poster_path,
            adult = entity.adult,
            overview = entity.overview,
            releaseDate = entity.release_date,
            genreIds = entity.genre_ids,
            id = entity.id,
            originalTitle = entity.original_title,
            originalLanguage = entity.original_language,
            title = entity.title,
            backdropPath = entity.backdrop_path,
            popularity = entity.popularity,
            voteCount = entity.vote_count,
            video = entity.video,
            voteAverage = entity.vote_average,
            favourite = favourite
    )
}

fun transform(model: MovieModel): MovieEntity {
    return MovieEntity(
            poster_path = model.posterPath,
            adult = model.adult,
            overview = model.overview,
            release_date = model.releaseDate,
            genre_ids = model.genreIds,
            id = model.id,
            original_title = model.originalTitle,
            original_language = model.originalLanguage,
            title = model.title,
            backdrop_path = model.backdropPath,
            popularity = model.popularity,
            vote_count = model.voteCount,
            video = model.video,
            vote_average = model.voteAverage
    )
}
