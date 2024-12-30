package com.ottmatt.preffectfitnesstracker.persistence.remote

import kotlinx.coroutines.delay

class StubbedFitnessGoalService: FitnessGoalService {
    override suspend fun getStepCountGoal(): Int {
        delay(2000) // sleep to emulate data retrieval from the backend.
        return 10000
    }
}