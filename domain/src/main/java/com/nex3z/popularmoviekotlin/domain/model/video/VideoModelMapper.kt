package com.nex3z.popularmoviekotlin.domain.model.video

import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import com.nex3z.popularmoviekotlin.data.entity.video.VideoEntity

fun transform(response: GetMovieVideosResponse): List<VideoModel> {
    return response.results.map(::transform)
}

fun transform(entity: VideoEntity): VideoModel {
    return VideoModel(
            id = entity.id,
            iso6391 = entity.iso_639_1,
            iso31661 = entity.iso_3166_1,
            key = entity.key,
            name = entity.name,
            site = entity.site,
            size = entity.size,
            type = entity.type
    )
}
