package com.ottmatt.preffectstepcounter.di

import com.ottmatt.preffectstepcounter.persistence.local.DailyStepsService
import com.ottmatt.preffectstepcounter.persistence.remote.PersonalDailyGoalService
import com.ottmatt.preffectstepcounter.persistence.local.StubbedDailyStepsService
import com.ottmatt.preffectstepcounter.persistence.remote.StubbedPersonalDailyGoalService
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
    fun getDailyStepsService(): DailyStepsService {
        return StubbedDailyStepsService()
    }

    @Provides
    @ViewModelScoped
    fun getPersonalDailyGoalService(): PersonalDailyGoalService {
        return StubbedPersonalDailyGoalService()
    }
}