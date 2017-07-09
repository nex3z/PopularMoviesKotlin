package com.nex3z.popularmovieskotlin.presentation.ui.detail.video

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    var videos: List<VideoModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.item_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videos[position]
        holder.itemView.tv_video_title.text = video.name
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onItemClickListener?.onItemClick(adapterPosition) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}