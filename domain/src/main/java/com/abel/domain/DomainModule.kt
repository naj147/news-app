package com.abel.domain

import org.koin.dsl.module


val domainModule = module {
    factory<GetArticlesUseCase>{
        GetArticlesUseCaseImpl(get())
    }
    factory<GetCategoryUseCase>{
        GetCategoryUseCaseImpl(get())
    }
}
