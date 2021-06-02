package com.abel.remote

import com.abel.remote.NetworkConst.EVERYTHING_URL
import com.abel.remote.NetworkConst.TOP_HEADLINES_URL
import com.abel.remote.model.NewsRemoteModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    //Fetching news either by category or by keyword depending what the api supports
    @GET(TOP_HEADLINES_URL)
    suspend fun fetchNewsByCategory(
        @Query("q") keyword: String?,
        @Query("category") category: String?,
        @Query("page") page: Int?
    ): NewsRemoteModel
//
//    //Fetching news  either by popularity or by newest first
//    @GET(EVERYTHING_URL)
//    suspend fun fetchNewsBySortParam(
//        @Query("sortBy") sort: String,
//        @Query("page") page: Int?
//    ): NewsRemoteModel
}
