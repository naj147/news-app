package com.abel.domain

import com.abel.common.model.News
import com.abel.common.model.SearchParam
import com.abel.domain.repository.NewsRepository

interface GetNewsUseCase {
    suspend fun execute(
        searchParam: SearchParam
    ): List<News>
}


internal class GetNewsUseCaseImpl(private val newsRepository: NewsRepository) : GetNewsUseCase {
    override suspend fun execute(
        searchParam: SearchParam
    ): List<News> = newsRepository.getNews(searchParam)

}
