package com.ottmatt.preffectfitnesstracker.persistence.remote

/**
 * Provides data on the user's fitness goals.
 */
interface FitnessGoalService {
    fun getStepCountGoal(): Int
}