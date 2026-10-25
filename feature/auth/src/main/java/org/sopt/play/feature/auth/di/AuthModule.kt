package org.sopt.play.feature.auth.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sopt.play.feature.auth.AuthViewModel

val authModule = module {
    viewModelOf(::AuthViewModel)
}
