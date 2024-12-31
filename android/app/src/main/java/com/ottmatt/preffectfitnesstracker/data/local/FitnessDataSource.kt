package com.ottmatt.preffectfitnesstracker.data.local

import com.ottmatt.preffectfitnesstracker.data.DataSourceResult

/**
 * Provides current statistics for the user's fitness level.
 */
interface FitnessDataSource {
    /**
     * @return the user's current step count for the day.
     */
    suspend fun getStepCount(): DataSourceResult<Int>
}