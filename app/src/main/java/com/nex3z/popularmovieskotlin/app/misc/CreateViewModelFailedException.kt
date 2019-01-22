package com.nex3z.popularmovieskotlin.app.misc


class CreateViewModelFailedException(
    message: String,
    cause: Throwable
) : RuntimeException(message, cause)
