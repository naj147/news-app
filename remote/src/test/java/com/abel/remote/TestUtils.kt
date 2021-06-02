package com.abel.remote

import com.abel.remote.model.ArticleRemoteModel
import com.abel.remote.model.NewsRemoteModel
import com.abel.remote.model.Source


private class TestUtils // Used to provide class Loader inside test folder

/**
 * Reads the content (UTF-8 encoding used) of the Json file provided
 */
fun String.readJson() = TestUtils::class.java.classLoader
    .getResourceAsStream(this)
    .bufferedReader()
    .use { it.readText() }

// Dummy objects filled with placeholder information for tests
internal fun getDummyNewsRemoteModel(): NewsRemoteModel = NewsRemoteModel(
        447, listOf(
                ArticleRemoteModel(
                        Source(null, "The Guardian"),
                        "UK Covid live news: country in ‘perilous moment’, says former chief scientific adviser - The Guardian",
                        "https://i.guim.co.uk/img/media/93aa351e1050efb0c62f07182affe190e7ce1f3a/0_233_3500_2101/master/3500.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctbGl2ZS5wbmc&enable=upscale&s=2589daf3a1c158feb4d35b616256e23d",
                        "2021-06-01T10:41:00Z",
                        null
                ),
                ArticleRemoteModel(
                        Source("bbc-news", "BBC News"),
                        "Daniel Boulton: Manhunt after mother and son found dead in Louth - BBC News",
                        "https://ichef.bbci.co.uk/news/1024/branded_news/13C0A/production/_118760908_danielboulton.jpg",
                        "2021-06-01T10:36:12Z",
                        "image copyrightLincolnshire Police\r\nimage captionPolice urged the public not to approach Daniel Boulton but instead to phone 999\r\nA manhunt has been launched after a mother and her nine-year-old son … [+1473 chars]"
                )
        )
)
