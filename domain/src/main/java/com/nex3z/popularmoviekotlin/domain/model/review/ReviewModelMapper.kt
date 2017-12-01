package com.nex3z.popularmoviekotlin.domain.model.review

import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.review.ReviewEntity

fun GetMovieReviewsResponse.transform(): List<ReviewModel> {
    return this.results.map(ReviewEntity::transform)
}

fun ReviewEntity.transform(): ReviewModel {
    return ReviewModel(
            id = this.id,
            author = this.author,
            content = this.content,
            url = this.url
    )

}
