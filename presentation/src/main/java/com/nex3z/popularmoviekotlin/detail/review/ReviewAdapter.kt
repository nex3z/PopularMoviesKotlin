package com.nex3z.popularmoviekotlin.detail.review

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nex3z.popularmoviekotlin.R
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    var reviews: List<ReviewModel> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent?.context)
        val itemView = inflater.inflate(R.layout.item_review, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder == null) {
            return
        }
        val review = reviews[position]
        holder.itemView.tv_item_review_author.text = review.author
        holder.itemView.tv_item_review_content.text = review.content
    }

    override fun getItemCount(): Int = reviews.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}