package com.ottmatt.preffectfitnesstracker.di

import android.content.Context
import android.content.res.Resources
import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import com.ottmatt.preffectfitnesstracker.persistence.local.StubbedFitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.RemoteFitnessGoalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun getFitnessService(): FitnessService {
        return StubbedFitnessService()
    }

    @Provides
    @ViewModelScoped
    fun getFitnessGoalService(httpClient: HttpClient): FitnessGoalService {
        return RemoteFitnessGoalService(httpClient)
    }

    @Provides
    @ViewModelScoped
    fun getResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    @ViewModelScoped
    fun getCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}