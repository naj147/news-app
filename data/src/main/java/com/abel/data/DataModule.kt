package com.abel.data

import com.abel.domain.repository.CategoryRepository
import com.abel.domain.repository.ArticlesRepository
import org.koin.dsl.module


val dataModule = module {
    factory<ArticlesRepository> { ArticlesRepositoryImpl(get()) }
    factory<CategoryRepository> { CategoryRepositoryImpl(get()) }
}
