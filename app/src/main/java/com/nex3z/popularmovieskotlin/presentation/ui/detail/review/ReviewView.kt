package com.nex3z.popularmovieskotlin.presentation.ui.detail.review

import com.nex3z.popularmovieskotlin.domain.model.review.ReviewModel
import com.nex3z.popularmovieskotlin.presentation.ui.base.BaseView
import com.nex3z.popularmovieskotlin.presentation.ui.base.LoadDataView

interface ReviewView : BaseView, LoadDataView {

    fun renderReviews(reviews: List<ReviewModel>)

}