package org.sopt.play.feature.home.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sopt.play.feature.home.HomeViewModel

val homeModule = module {
    viewModelOf(::HomeViewModel)
}
