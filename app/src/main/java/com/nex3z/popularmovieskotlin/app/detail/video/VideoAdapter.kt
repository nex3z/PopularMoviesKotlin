package com.nex3z.popularmovieskotlin.app.detail.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.app.misc.OnItemClickListener
import com.nex3z.popularmovieskotlin.domain.model.video.VideoModel
import kotlinx.android.synthetic.main.item_video.view.*

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener<VideoModel>? = null

    var videos: List<VideoModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_video, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videos[position]
        holder.itemView.tv_item_video_title.text = video.name
    }

    override fun getItemCount(): Int = videos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.invoke(videos[adapterPosition])
            }
        }
    }

}