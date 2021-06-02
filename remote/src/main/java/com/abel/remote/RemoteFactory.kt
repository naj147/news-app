package com.abel.remote

import com.abel.common.model.OkHttpCache
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteFactory {

    /**
     * Builds given [T] retrofit restApi interface
     *
     * @param baseUrl [String] with a valid baseUrl including http scheme
     * @param restApi [T] with Retrofit interface
     * @param okHttpClient [OkHttpClient] to use when accessing this rest api
     * todo: generalise the MoshiConverterFactory
     */
    fun <T> buildRestApi(
        baseUrl: String,
        restApi: Class<T>,
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): T {
        return buildService(
            baseUrl,
            restApi,
            moshiConverterFactory,
            okHttpClient
        )
    }

    /**
     * Builds an [OkHttpClient] with the given interceptors attached to it
     *
     * @param restApiInterceptors [List] of [Interceptor] to attach to the expected client
     * @param restApiNetworkInterceptors [List] of network [Interceptor] to attach to the expected client
     * TODO:  Introduce a caching solution (check  bookmarks)
     */
    fun buildOkHttpClient(
        restApiInterceptors: List<Interceptor>?,
        restApiNetworkInterceptors: List<Interceptor>?,
        okHttpCache: OkHttpCache<Cache>?
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                restApiInterceptors?.forEach { addInterceptor(it) }
                restApiNetworkInterceptors?.forEach { addNetworkInterceptor(it) }
                okHttpCache?.let { okCache ->
                    cache(okCache.getCache())
                    addInterceptor { chain ->
                        // Get the request from the chain.
                        var request = chain.request()
                        /*
                        *  we initialize the request and change its header depending on whether
                        *  the device is connected to Internet or not.
                        */
                        request = if (okCache.hasNetwork())
                        /*
                        *  If there is Internet, get the cache that was stored 5 seconds ago.
                        *  If the cache is older than 5 seconds, then discard it,
                        *  and indicate an error in fetching the response.
                        *  The 'max-age' attribute is responsible for this behavior.
                        */
                            request.newBuilder().header("Cache-Control", "public, max-age=" + 60)
                                .removeHeader("Pragma").removeHeader("Vary")
                                .build()
                        else
                        /*
                        *  If there is no Internet, get the cache that was stored 7 days ago.
                        *  If the cache is older than 7 days, then discard it,
                        *  and indicate an error in fetching the response.
                        *  The 'max-stale' attribute is responsible for this behavior.
                        *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                        */
                            request.newBuilder().removeHeader("Pragma").removeHeader("Vary").header(
                                "Cache-Control",
                                "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                            ).removeHeader("Pragma").build()
                        // Add the modified request to the chain.
                        chain.proceed(request)
                    }
                }
            }
            .build()
    }

    private fun <T> buildService(
        baseUrl: String,
        restApi: Class<T>,
        moshiConverterFactory: MoshiConverterFactory,
        okHttpClient: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build()
            .create(restApi)
    }
}
