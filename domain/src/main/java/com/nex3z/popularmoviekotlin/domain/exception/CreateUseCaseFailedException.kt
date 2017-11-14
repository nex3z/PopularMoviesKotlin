package com.nex3z.popularmoviekotlin.domain.exception

class CreateUseCaseFailedException : RuntimeException {

    constructor(cause: Throwable) : super(cause)

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(message: String) : super(message)

}