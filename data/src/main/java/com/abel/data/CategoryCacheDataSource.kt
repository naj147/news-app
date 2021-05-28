package com.abel.data

interface CategoryCacheDataSource {
    suspend fun getCategories(): List<String>
    suspend fun getKeywords(): List<String>
}
