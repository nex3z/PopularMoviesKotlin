package com.nex3z.popularmoviekotlin.domain.model.movie

import com.nex3z.popularmoviekotlin.data.entity.discover.DiscoverMovieResponse
import com.nex3z.popularmoviekotlin.data.entity.discover.MovieEntity

fun transform(response: DiscoverMovieResponse): List<MovieModel> {
    return response.results.map(::transform)
}

fun transform(entity: MovieEntity): MovieModel {
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
            favourite = false
    );
}
