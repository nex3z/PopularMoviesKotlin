package com.nex3z.popularmovieskotlin.app.discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nex3z.popularmovieskotlin.app.R
import com.nex3z.popularmovieskotlin.domain.model.movie.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var onMovieClickListener: OnMovieClickListener? = null

    var movies: List<MovieModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.itemView.tv_item_movie_vote.text = movie.voteAverage.toString()
        holder.itemView.cb_item_movie_favourite.isChecked = movie.favourite
        Picasso.get()
            .load(movie.getPosterUrl(MovieModel.PosterSize.W342))
            .into(holder.itemView.iv_item_movie_poster)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.iv_item_movie_poster.setOnClickListener {
                onMovieClickListener?.onMovieClick(adapterPosition, itemView.iv_item_movie_poster)
            }
            itemView.cb_item_movie_favourite.setOnClickListener {
                onMovieClickListener?.onFavouriteClick(adapterPosition)
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(position: Int, poster: View)
        fun onFavouriteClick(position: Int)
    }

}