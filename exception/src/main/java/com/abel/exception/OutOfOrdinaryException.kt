package com.abel.exception

data class OutOfOrdinaryException(val throwable: Throwable? = null) : RuntimeException()
