package com.ottmatt.preffectfitnesstracker.persistence.local

/**
 * Provides current statistics for the user's fitness level.
 */
interface FitnessService {
    /**
     * Loads the fitness
     */
    fun getStepCount(): Int
}