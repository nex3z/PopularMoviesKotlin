package com.nex3z.popularmovieskotlin.domain.model.review

import com.nex3z.popularmovieskotlin.data.entity.review.GetMovieReviewsResp
import com.nex3z.popularmovieskotlin.data.entity.review.ReviewEntity

fun GetMovieReviewsResp.transform(): List<ReviewModel> {
    return this.results.map(ReviewEntity::transform)
}

fun ReviewEntity.transform(): ReviewModel {
    return ReviewModel(
        author = this.author,
        content = this.content,
        id = this.id,
        url = this.url
    )
}
