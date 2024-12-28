package com.ottmatt.preffectfitnesstracker.persistence.local

class StubbedFitnessService: FitnessService {
    override fun getCurrentDailySteps(): Int {
        return 100
    }
}