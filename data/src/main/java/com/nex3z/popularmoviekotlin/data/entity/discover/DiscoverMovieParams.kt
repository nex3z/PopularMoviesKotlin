package com.nex3z.popularmoviekotlin.data.entity.discover

class DiscoverMovieParams(val page: Int, val sortBy: SortBy) {
    val SORT_BY_RELEASE_DATE_DESC = "release_date.desc"
    val SORT_BY_POPULARITY_DESC = "popularity.desc"

    val mParams = mutableMapOf<String, String>()

    init {
        mParams.put("page", page.toString())
        mParams.put("sort_by", sortBy.value)
    }

    sealed class SortBy(val value: String) {
        class ReleaseDateDesc() : SortBy("release_date.desc")
        class PopularityDesc() : SortBy("popularity.desc")
    }

}