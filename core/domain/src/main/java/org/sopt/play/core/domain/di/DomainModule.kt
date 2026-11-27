package org.sopt.play.core.domain.di

import org.koin.dsl.module
import org.sopt.play.core.domain.repository.AuthRepository
import org.sopt.play.core.domain.repository.LikeRepository
import org.sopt.play.core.domain.usecase.CreatePostUseCase
import org.sopt.play.core.domain.usecase.DeletePostUseCase
import org.sopt.play.core.domain.usecase.GetPostsUseCase
import org.sopt.play.core.domain.usecase.IssueAuthCodeUseCase
import org.sopt.play.core.domain.usecase.UpdatePostUseCase

val domainModule = module {
    single { AuthRepository(get(), get()) }
    single { LikeRepository(get()) }

    factory { GetPostsUseCase(get()) }
    factory { CreatePostUseCase(get()) }
    factory { UpdatePostUseCase(get()) }
    factory { DeletePostUseCase(get()) }
    factory { IssueAuthCodeUseCase(get()) }
}
