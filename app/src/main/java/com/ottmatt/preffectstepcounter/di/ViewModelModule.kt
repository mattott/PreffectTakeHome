package com.ottmatt.preffectstepcounter.di

import com.ottmatt.preffectstepcounter.persistence.local.FitnessService
import com.ottmatt.preffectstepcounter.persistence.remote.FitnessGoalService
import com.ottmatt.preffectstepcounter.persistence.local.StubbedFitnessService
import com.ottmatt.preffectstepcounter.persistence.remote.StubbedFitnessGoalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun getDailyStepsService(): FitnessService {
        return StubbedFitnessService()
    }

    @Provides
    @ViewModelScoped
    fun getPersonalDailyGoalService(): FitnessGoalService {
        return StubbedFitnessGoalService()
    }
}