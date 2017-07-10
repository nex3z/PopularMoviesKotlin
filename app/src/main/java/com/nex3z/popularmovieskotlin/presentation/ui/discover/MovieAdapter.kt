package com.nex3z.popularmovieskotlin.presentation.ui.discover

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmovieskotlin.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onItemClickListener: OnItemClickListener? = null

    var movies: List<MovieModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.itemView.tv_title.text = movie.title
        val url = movie.getPosterImageUrl()
        Picasso.with(holder.itemView.context).load(url).into(holder.itemView.iv_poster)
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(adapterPosition, itemView.iv_poster)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, poster: View)
    }

}