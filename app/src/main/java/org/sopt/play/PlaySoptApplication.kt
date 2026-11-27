package org.sopt.play

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.sopt.play.core.datastore.di.datastoreModule
import org.sopt.play.core.domain.di.domainModule
import org.sopt.play.feature.auth.di.authModule
import org.sopt.play.feature.home.di.homeModule
import org.sopt.play.feature.search.di.searchModule

val appModules = listOf(datastoreModule, domainModule, authModule, homeModule, searchModule)

class PlaySoptApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PlaySoptApplication)
            modules(appModules)
        }
    }
}
