package com.nex3z.popularmovieskotlin.domain.model.video

import com.nex3z.popularmovieskotlin.data.entity.video.GetVideosResponse
import com.nex3z.popularmovieskotlin.data.entity.video.VideoEntity

object VideoModelMapper {

    fun transform(resp: GetVideosResponse): List<VideoModel> {
        return resp.results.map(VideoModelMapper::transform)
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

}
