package com.nex3z.popularmoviekotlin.domain.model.video

import com.nex3z.popularmoviekotlin.data.entity.video.GetMovieVideosResponse
import com.nex3z.popularmoviekotlin.data.entity.video.VideoEntity

fun GetMovieVideosResponse.transform(): List<VideoModel> {
    return this.results.map(VideoEntity::transform)
}

fun VideoEntity.transform(): VideoModel {
    return VideoModel(
            id = this.id,
            iso6391 = this.iso_639_1,
            iso31661 = this.iso_3166_1,
            key = this.key,
            name = this.name,
            site = this.site,
            size = this.size,
            type = this.type
    )

}
