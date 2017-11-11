package com.nex3z.popularmoviekotlin.data.entity.discover

class DiscoverMovieParams(val page: Int, val sortBy: SortBy) {

    val params = mutableMapOf<String, String>()

    init {
        params.put("page", page.toString())
        params.put("sort_by", sortBy.value)
    }

    sealed class SortBy(val value: String) {
        class ReleaseDateDesc() : SortBy("release_date.desc")
        class PopularityDesc() : SortBy("popularity.desc")
    }

}