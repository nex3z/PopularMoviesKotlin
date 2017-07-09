package com.nex3z.popularmovieskotlin.domain.model.review

import com.nex3z.popularmovieskotlin.data.entity.review.GetReviewsResponse
import com.nex3z.popularmovieskotlin.data.entity.review.ReviewEntity

object ReviewModelMapper {

    fun transform(resp: GetReviewsResponse): List<ReviewModel> {
        return resp.results.map(ReviewModelMapper::transform)
    }

    fun transform(entity: ReviewEntity): ReviewModel {
        return ReviewModel(
                id = entity.id,
                author = entity.author,
                content = entity.content,
                url = entity.url
        )
    }
}
