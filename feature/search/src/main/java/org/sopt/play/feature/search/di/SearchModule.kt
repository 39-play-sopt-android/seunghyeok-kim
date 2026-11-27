package org.sopt.play.feature.search.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.sopt.play.feature.search.SearchViewModel

val searchModule = module {
    viewModelOf(::SearchViewModel)
}
