package com.ottmatt.preffectfitnesstracker.data.repository

import com.ottmatt.preffectfitnesstracker.data.DataSourceResult
import com.ottmatt.preffectfitnesstracker.data.local.FitnessDataSource
import com.ottmatt.preffectfitnesstracker.data.remote.FitnessGoalsDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository providing views with data regarding the user's fitness and goals.
 */
class FitnessTrackerRepository @Inject constructor(
    private val fitnessDataSource: FitnessDataSource,
    private val fitnessGoalsDataSource: FitnessGoalsDataSource,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getStepCount(): DataSourceResult<Int> {
        return withContext(dispatcher) {
            fitnessDataSource.getStepCount()
        }
    }

    suspend fun getStepCountGoal(): DataSourceResult<Int> {
        return withContext(dispatcher) {
            fitnessGoalsDataSource.getStepCountGoal()
        }
    }
}
