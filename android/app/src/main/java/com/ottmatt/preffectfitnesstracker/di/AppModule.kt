package com.ottmatt.preffectfitnesstracker.di

import android.content.Context
import com.ottmatt.preffectfitnesstracker.BuildConfig
import com.ottmatt.preffectfitnesstracker.PreffectFitnessTrackerApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApp(@ApplicationContext context: Context): PreffectFitnessTrackerApplication {
        return context as PreffectFitnessTrackerApplication
    }

    @Singleton
    @Provides
    fun provideHttpClient() = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            url(BuildConfig.REMOTE_PREFFECT_URL)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(Json)
        }
    }
}