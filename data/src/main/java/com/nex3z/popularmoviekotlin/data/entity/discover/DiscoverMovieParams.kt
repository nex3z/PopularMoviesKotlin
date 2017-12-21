package com.nex3z.popularmoviekotlin.data.entity.discover

import com.nex3z.popularmoviekotlin.data.entity.BaseParams

class DiscoverMovieParams(
        val page: Int,
        val sortBy: SortBy
) : BaseParams() {

    override val params: Map<String, String>
        get() = mapOf(
                "page" to page.toString(),
                "sort_by" to sortBy.value
        )

    enum class SortBy(val value: String) {
        RELEASE_DATE_DESC("release_date.desc"),
        POPULARITY_DESC("popularity.desc")
    }

}