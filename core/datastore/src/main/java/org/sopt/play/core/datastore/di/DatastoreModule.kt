package org.sopt.play.core.datastore.di

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.sopt.play.core.datastore.UserLocalDataSource
import org.sopt.play.core.datastore.UserPreferences

val datastoreModule = module {
    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile("user_preferences.preferences_pb") }
        )
    }
    single { UserPreferences(get()) }
    single { UserLocalDataSource(get()) }
}
