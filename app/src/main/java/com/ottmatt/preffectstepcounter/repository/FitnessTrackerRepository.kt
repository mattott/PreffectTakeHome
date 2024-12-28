package com.ottmatt.preffectstepcounter.repository

import com.ottmatt.preffectstepcounter.persistence.local.FitnessService
import com.ottmatt.preffectstepcounter.persistence.remote.FitnessGoalService
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
