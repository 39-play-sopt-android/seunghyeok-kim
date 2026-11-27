package org.sopt.play.core.data.di

import org.koin.dsl.module
import org.sopt.play.core.data.repository.PostRepositoryImpl
import org.sopt.play.core.domain.repository.PostRepository

val dataModule = module {
    single<PostRepository> { PostRepositoryImpl(get(), get()) }
}
