package com.abel.data

import com.abel.domain.repository.CategoryRepository
import com.abel.domain.repository.NewsRepository
import org.koin.dsl.module


val dataModule = module {
    factory<NewsRepository> { NewsRepositoryImpl(get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get()) }
}
