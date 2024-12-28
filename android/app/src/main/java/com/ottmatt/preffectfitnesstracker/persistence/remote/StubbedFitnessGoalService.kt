package com.ottmatt.preffectfitnesstracker.persistence.remote

class StubbedFitnessGoalService: FitnessGoalService {
    override fun getDailyStepsGoal(): Int {
        return 6000
    }
}