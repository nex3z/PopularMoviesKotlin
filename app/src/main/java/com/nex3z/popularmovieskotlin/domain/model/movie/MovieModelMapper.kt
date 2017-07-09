package com.nex3z.popularmovieskotlin.domain.model.movie

import com.nex3z.popularmovieskotlin.data.entity.movie.DiscoverMovieResponse
import com.nex3z.popularmovieskotlin.data.entity.movie.MovieEntity

object MovieModelMapper {

    fun transform(resp: DiscoverMovieResponse): List<MovieModel> {
        return resp.results.map(MovieModelMapper::transform)
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
                voteAverage = entity.vote_average
        )
    }

}
