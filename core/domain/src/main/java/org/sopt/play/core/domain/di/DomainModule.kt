package org.sopt.play.core.domain.di

import org.koin.dsl.module
import org.sopt.play.core.domain.repository.AuthRepository

val domainModule = module {
    single { AuthRepository(get()) }
}
