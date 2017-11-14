package com.nex3z.popularmoviekotlin.domain

import com.nex3z.popularmoviekotlin.domain.interactor.BaseUseCase
import kotlin.reflect.KClass

interface UseCaseFactory {

    fun <T : BaseUseCase<*, *>> create(kClass: KClass<T>): T

}