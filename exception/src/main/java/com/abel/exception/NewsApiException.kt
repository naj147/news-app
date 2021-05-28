package com.abel.exception

class NewsApiException(
    val code: String,
    override val message: String,
    val status: String
) : RuntimeException()
