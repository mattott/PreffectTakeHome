package com.ottmatt.preffectfitnesstracker.repository

import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import javax.inject.Inject

class FitnessTrackerRepository @Inject constructor(
    private val fitnessService: FitnessService,
    private val fitnessGoalService: FitnessGoalService
) {
    fun getStepCount(): Int {
        return fitnessService.getStepCount()
    }

    fun getStepCountGoal(): Int {
        return fitnessGoalService.getStepCountGoal()
    }
}
