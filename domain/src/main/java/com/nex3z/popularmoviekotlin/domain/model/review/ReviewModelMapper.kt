package com.nex3z.popularmoviekotlin.domain.model.review

import com.nex3z.popularmoviekotlin.data.entity.review.GetMovieReviewsResponse
import com.nex3z.popularmoviekotlin.data.entity.review.ReviewEntity

fun transform(response: GetMovieReviewsResponse): List<ReviewModel> {
    return response.results.map(::transform)
}

fun transform(entity: ReviewEntity): ReviewModel {
    return ReviewModel(
            id = entity.id,
            author = entity.author,
            content = entity.content,
            url = entity.url
    )
}
