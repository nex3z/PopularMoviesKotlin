package com.nex3z.popularmovieskotlin.data.entity

abstract class BasePagedResponse<out T> : BaseListResponse<T>() {

    var page: Int = 0
    var total_results: Int = 0
    var total_pages: Int = 0

}