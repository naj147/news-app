package com.abel.domain

import org.koin.dsl.module


val domainModule = module {
    factory<GetFavoriteNewsUseCase>{
        GetNewsUseCaseImpl(get())
    }
}
