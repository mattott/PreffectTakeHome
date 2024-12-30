package com.ottmatt.preffectfitnesstracker.repository

import com.ottmatt.preffectfitnesstracker.persistence.ApiResult
import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository providing views with data regarding the user's fitness and goals.
 */
class FitnessTrackerRepository @Inject constructor(
    private val fitnessService: FitnessService,
    private val fitnessGoalService: FitnessGoalService,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getStepCount(): ApiResult<Int> {
        return withContext(dispatcher) {
            fitnessService.getStepCount()
        }
    }

    suspend fun getStepCountGoal(): ApiResult<Int> {
        return withContext(dispatcher) {
            fitnessGoalService.getStepCountGoal()
        }
    }
}
