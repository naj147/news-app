package com.abel.remote

import com.abel.exception.NewsApiException
import com.abel.exception.OutOfOrdinaryException
import com.squareup.moshi.JsonEncodingException
import com.squareup.moshi.JsonReader
import okio.Buffer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NewsApiAdapterTest {
    private val newsApiAdapter = NewsApiAdapter()

    @Test
    fun `should parse successfully`() {
        val dummyResult = getDummyNewsRemoteModel()
        val json = "json/success.json".readJson()
        val parsingResult = parseJsonToNewsRemoteModel(json)
        assertThat(parsingResult.totalResults == dummyResult.totalResults)
        assertThat(parsingResult.articles).isEqualTo(
            dummyResult.articles
        )
    }

    @Test(expected = NewsApiException::class)
    fun `should throw a NewsApiException if state is fail`() {
        val json = "json/failure.json".readJson()
        parseJsonToNewsRemoteModel(json)
    }

    @Test(expected = OutOfOrdinaryException::class)
    fun `should throw an OutOfOrdinaryException if json model is not handled`() {
        val json = "{}"
        parseJsonToNewsRemoteModel(json)
    }

    @Test(expected = JsonEncodingException::class)
    fun `should throw an JsonEncodingException if json is not parse-able`() {
        val json = "{]" //invalid json
        parseJsonToNewsRemoteModel(json)
    }

    private fun parseJsonToNewsRemoteModel(json: String) =
        newsApiAdapter.fromJson(
            JsonReader.of(Buffer().writeUtf8(json))
        )
}
