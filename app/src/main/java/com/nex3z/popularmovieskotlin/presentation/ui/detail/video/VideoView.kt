package com.nex3z.popularmovieskotlin.presentation.ui.detail.video

import android.net.Uri
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseView
import com.nex3z.popularmovieskotlin.presentation.ui.base.LoadDataView

interface VideoView : BaseView, LoadDataView {

    fun renderVideos(videos: List<VideoModel>)

    fun playVideo(uri: Uri)

}