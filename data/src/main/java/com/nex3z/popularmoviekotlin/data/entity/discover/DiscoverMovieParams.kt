package com.nex3z.popularmoviekotlin.data.entity.discover

class DiscoverMovieParams(page: Int, sortBy: SortBy) {

    val params = mutableMapOf<String, String>()

    var page: Int = 1
        set(value) {
            field = value
            params.put("page", value.toString())
        }

    var sortBy: SortBy = SortBy.RELEASE_DATE_DESC
        set(value) {
            field = value
            params.put("sort_by", sortBy.value)
        }

    init {
        this@DiscoverMovieParams.page = page
        this@DiscoverMovieParams.sortBy = sortBy
    }

    enum class SortBy(val value: String) {
        RELEASE_DATE_DESC("release_date.desc"),
        POPULARITY_DESC("popularity.desc")
    }

}