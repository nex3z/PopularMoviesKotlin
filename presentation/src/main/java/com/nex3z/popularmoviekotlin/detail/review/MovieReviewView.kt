package com.nex3z.popularmoviekotlin.detail.review

import com.nex3z.popularmoviekotlin.base.BaseView
import com.nex3z.popularmoviekotlin.base.LoadDataView
import com.nex3z.popularmoviekotlin.domain.model.review.ReviewModel

interface MovieReviewView : BaseView, LoadDataView {

    fun renderReviews(reviews: List<ReviewModel>)

}