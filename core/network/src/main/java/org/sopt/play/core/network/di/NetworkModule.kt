package org.sopt.play.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.sopt.play.core.datastore.UserPreferences
import org.sopt.play.core.network.BuildConfig
import org.sopt.play.core.network.interceptor.AuthInterceptor
import org.sopt.play.core.network.service.MemberService
import org.sopt.play.core.network.service.PostService
import retrofit2.Retrofit

val networkModule = module {
    single {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single {
        AuthInterceptor {
            val userPreferences: UserPreferences = get()
            runBlocking { userPreferences.authCodeFlow.first() }
        }
    }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<Json>().asConverterFactory(contentType))
            .build()
    }

    single<PostService> {
        get<Retrofit>().create(PostService::class.java)
    }

    single<MemberService> {
        get<Retrofit>().create(MemberService::class.java)
    }
}
