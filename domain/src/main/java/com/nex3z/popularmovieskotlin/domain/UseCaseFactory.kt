package com.nex3z.popularmovieskotlin.domain

import com.nex3z.popularmovieskotlin.domain.interactor.BaseUseCase

interface UseCaseFactory {

    fun <T : BaseUseCase<*, *>> create(factory: (Context) -> T): T

}