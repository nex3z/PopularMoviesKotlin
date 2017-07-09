package com.nex3z.popularmovieskotlin.data.entity.review

data class GetReviewsResponse(
        val id: Long = 0,
        val page: Int = 0,
        val results: List<ReviewEntity> = emptyList(),
        val total_results: Int = 0,
        val total_pages: Int = 0
)