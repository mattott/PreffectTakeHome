package com.ottmatt.preffectfitnesstracker.repository

import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FitnessTrackerRepository @Inject constructor(
    private val fitnessService: FitnessService,
    private val fitnessGoalService: FitnessGoalService,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getStepCount(): Int {
        return withContext(dispatcher) {
            fitnessService.getStepCount()
        }
    }

    suspend fun getStepCountGoal(): Int {
        return withContext(dispatcher)  {
            fitnessGoalService.getStepCountGoal()
        }
    }
}
