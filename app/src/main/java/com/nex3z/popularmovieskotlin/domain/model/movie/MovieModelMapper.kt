package com.nex3z.popularmovieskotlin.domain.model.movie

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import com.nex3z.popularmovieskotlin.data.entity.movie.MovieEntity

object MovieModelMapper {

    fun transform(entity: DiscoverMovieResponse): List<MovieModel> {
        if (entity.results != null) {
            return entity.results.map(this::transform)
        } else {
            return emptyList();
        }
    }

    fun transform(entity: MovieEntity): MovieModel {
        val movieModel: MovieModel = MovieModel(
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
                voteAverage = entity.vote_average
        )

        return movieModel
    }

}
