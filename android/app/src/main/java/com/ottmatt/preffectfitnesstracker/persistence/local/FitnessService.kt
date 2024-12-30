package com.ottmatt.preffectfitnesstracker.persistence.local

import com.ottmatt.preffectfitnesstracker.persistence.ApiResult

/**
 * Provides current statistics for the user's fitness level.
 */
interface FitnessService {
    /**
     * @return the user's current step count for the day.
     */
    suspend fun getStepCount(): ApiResult<Int>
}