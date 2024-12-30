package com.ottmatt.preffectfitnesstracker.repository

import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import javax.inject.Inject

class FitnessTrackerRepository @Inject constructor(
    private val fitnessService: FitnessService,
    private val fitnessGoalService: FitnessGoalService
) {
    fun loadCurrentFitness(): Int {
        return fitnessService.getCurrentDailySteps()
    }

    fun loadFitnessGoal(): Int {
        return fitnessGoalService.getDailyStepsGoal()
    }
}
