package com.abel.remote

import com.abel.exception.NewsApiException
import com.abel.exception.OutOfOrdinaryException
import com.abel.remote.model.NewsRemoteModel
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class NewsApiAdapter {
    private val responseAdapter: JsonAdapter<NewsRemoteModel>
    private val errorAdapter: JsonAdapter<NewsApiException>

    init {
        val moshiInstance = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        responseAdapter = moshiInstance.adapter(NewsRemoteModel::class.java)
        errorAdapter = moshiInstance.adapter(NewsApiException::class.java)
    }

    @FromJson
    fun fromJson(
        reader: JsonReader
    ): NewsRemoteModel {
        try {
            return responseAdapter.fromJson(reader.peekJson())!!
        } catch (ignored: JsonDataException) {
            try {
                errorAdapter.fromJson(reader.peekJson())
            } catch (e: Throwable) {
                OutOfOrdinaryException(e)
            }?.let {
                throw it
            } ?: throw OutOfOrdinaryException()
        } catch (t: Throwable) {
            throw OutOfOrdinaryException(t)
        } finally {
            reader.beginObject()
            while(reader.hasNext())
                reader.skipValue()
            reader.endObject()
        }
    }

    @Suppress("Unused", "UNUSED_PARAMETER")
    @ToJson
    fun toJson(
        writer: JsonWriter,
        content: NewsRemoteModel?
    ) {
        throw UnsupportedOperationException("Cannot deserialize NewsRemoteModel")
    }
}
