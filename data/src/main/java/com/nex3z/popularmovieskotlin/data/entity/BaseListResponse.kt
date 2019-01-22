package com.nex3z.popularmovieskotlin.data.entity

abstract class BaseListResponse<out T> : BaseResponse() {
    val results: List<T> = emptyList()
}
