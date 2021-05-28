package com.abel.remote

import com.abel.remote.NetworkConst.API_KEY_PARAMETER
import com.abel.remote.NetworkConst.LANGUAGE_DEFAULT_VAL
import com.abel.remote.NetworkConst.LANGUAGE_PARAMETER
import com.abel.remote.NetworkConst.PAGE_SIZE_DEFAULT_VAL
import com.abel.remote.NetworkConst.PAGE_SIZE_PARAMETER
import okhttp3.Interceptor
import okhttp3.Response

internal class NewsQueryParameterInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            with(chain.request()) {
                newBuilder().url(
                    url.newBuilder()
                        .addQueryParameter(API_KEY_PARAMETER, apiKey)
                        .addQueryParameter(PAGE_SIZE_PARAMETER, PAGE_SIZE_DEFAULT_VAL)
                        .addQueryParameter(LANGUAGE_PARAMETER, LANGUAGE_DEFAULT_VAL)
                        .build()
                ).build()
            }
        )
    }
}
