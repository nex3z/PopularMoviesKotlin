package com.nex3z.popularmovieskotlin.domain.model.video

import com.nex3z.popularmovieskotlin.data.entity.video.GetMovieVideosResp
import com.nex3z.popularmovieskotlin.data.entity.video.VideoEntity

fun GetMovieVideosResp.transform(): List<VideoModel> {
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
