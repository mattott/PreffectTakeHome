package com.ottmatt.preffectfitnesstracker.data.remote

import com.ottmatt.preffectfitnesstracker.data.DataSourceResult

/**
 * Provides data on the user's fitness goals.
 */
interface FitnessGoalsDataSource {
    /**
     * @return the user's daily step count goal.
     */
    suspend fun getStepCountGoal(): DataSourceResult<Int>
}