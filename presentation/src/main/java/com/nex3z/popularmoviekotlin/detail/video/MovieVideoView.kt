package com.nex3z.popularmoviekotlin.detail.video

import com.nex3z.popularmoviekotlin.base.BaseView
import com.nex3z.popularmoviekotlin.base.LoadDataView
import com.nex3z.popularmoviekotlin.domain.model.video.VideoModel

interface MovieVideoView : BaseView, LoadDataView {

    fun renderVideos(videos: List<VideoModel>)

    fun playVideo(video: VideoModel)

}