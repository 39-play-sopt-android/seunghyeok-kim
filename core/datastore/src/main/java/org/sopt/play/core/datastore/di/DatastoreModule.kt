package org.sopt.play.core.datastore.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.sopt.play.core.datastore.LikeLocalDataSource
import org.sopt.play.core.datastore.LikePreferences
import org.sopt.play.core.datastore.UserLocalDataSource
import org.sopt.play.core.datastore.UserPreferences

val datastoreModule = module {
    single {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile("user_preferences.preferences_pb") }
        )
    }
    single<DataStore<Preferences>>(named("likeDataStore")) {
        PreferenceDataStoreFactory.create(
            produceFile = { androidContext().preferencesDataStoreFile("like_preferences.preferences_pb") }
        )
    }
    single { UserPreferences(get()) }
    single { UserLocalDataSource(get()) }

    single { LikePreferences(get(named("likeDataStore"))) }
    single { LikeLocalDataSource(get()) }
}
