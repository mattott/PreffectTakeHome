package com.ottmatt.preffectfitnesstracker.di

import android.content.Context
import com.ottmatt.preffectfitnesstracker.PreffectFitnessTrackerApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideApp(@ApplicationContext context: Context): PreffectFitnessTrackerApplication {
        return context as PreffectFitnessTrackerApplication
    }
}