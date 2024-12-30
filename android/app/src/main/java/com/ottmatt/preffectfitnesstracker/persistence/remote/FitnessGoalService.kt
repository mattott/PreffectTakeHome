package com.ottmatt.preffectfitnesstracker.persistence.remote

/**
 * Provides data on the user's fitness goals.
 */
interface FitnessGoalService {
    /**
     * @return the user's daily step count goal.
     */
    suspend fun getStepCountGoal(): Int
}