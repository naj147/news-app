package com.abel.common.model

interface OkHttpCache<V> {
    fun getCache(): V
    fun hasNetwork(): Boolean
}
