package com.abel.exception

data class NewsApiException(
    val code: String,
    override val message: String,
    val status: String
) : RuntimeException()
