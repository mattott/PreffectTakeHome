package com.ottmatt.preffectfitnesstracker.repository

import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import javax.inject.Inject

class FitnessTrackerRepository @Inject constructor(
    private val fitnessService: FitnessService,
    private val fitnessGoalService: FitnessGoalService
) {
    suspend fun getCurrentDailySteps(): Int {
        return fitnessService.getCurrentDailySteps()
    }

    suspend fun getPersonalDailyStepsGoal(): Int {
        return fitnessGoalService.getDailyStepsGoal()
    }
}
