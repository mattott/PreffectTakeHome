package com.ottmatt.preffectfitnesstracker.persistence.remote

class StubbedFitnessGoalService: FitnessGoalService {
    override fun getStepCountGoal(): Int {
        return 6000
    }
}