package com.abel.remote

import com.abel.common.BOOLEAN_DEBUG
import com.abel.common.INTERCEPTOR_LOGGING
import com.abel.common.INTERCEPTOR_NEWS_API_PARAMETERS
import com.abel.common.NEWS_API_OKHTTP_CLIENT
import com.abel.data.NewsRemoteDataSource
import com.abel.remote.NetworkConst.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.coroutines.CoroutineContext

private const val IO_CONTEXT = "TAG_IO_CONTEXT"

val remoteModule = module {

    factory<NewsRemoteDataSource> {
        NewsRemoteDataSourceImpl(get(), get(named(IO_CONTEXT)))
    }

    factory<CoroutineContext>(named(IO_CONTEXT)) { Dispatchers.IO }

    single(named(NEWS_API_OKHTTP_CLIENT)) {
        RemoteFactory.buildOkHttpClient(
            listOf(get(named(INTERCEPTOR_NEWS_API_PARAMETERS))),
            listOf(get(named(INTERCEPTOR_LOGGING)))
        )
    }

    factory {
        RemoteFactory.buildRestApi(
            BASE_URL,
            NewsService::class.java,
            get(),
            get(named(NEWS_API_OKHTTP_CLIENT))
        )
    }

    single {
        Moshi.Builder()
            .add(NewsApiAdapter())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        MoshiConverterFactory.create(get())
    }

    factory<Interceptor>(named(INTERCEPTOR_LOGGING)) {
        val isDebug: Boolean = get(named(BOOLEAN_DEBUG))
        HttpLoggingInterceptor().apply {
            level =
                if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    factory<Interceptor>(named(INTERCEPTOR_NEWS_API_PARAMETERS)) {
        // This api key is usually stored as CI/CD tool secret and/or in local.properties for test purposes
        // but for the sake of simplicity it's here
        NewsQueryParameterInterceptor("6c4b65ed5a8e453ca97247ab12274330")
    }
}
