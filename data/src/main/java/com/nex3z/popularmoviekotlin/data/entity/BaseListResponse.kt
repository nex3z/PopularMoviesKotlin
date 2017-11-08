package com.nex3z.popularmoviekotlin.data.entity

abstract class BaseListResponse<out T>() : BaseResponse() {
    val results: List<T> = emptyList()
}