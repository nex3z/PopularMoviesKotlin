package com.nex3z.popularmoviekotlin.domain

import com.nex3z.popularmoviekotlin.domain.interactor.BaseUseCase

interface UseCaseFactory {

    fun <T : BaseUseCase<*, *>> create(factory: (Context) -> T): T

}