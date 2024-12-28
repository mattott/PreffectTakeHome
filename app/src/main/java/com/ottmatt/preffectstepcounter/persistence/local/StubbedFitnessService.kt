package com.ottmatt.preffectstepcounter.persistence.local

class StubbedFitnessService: FitnessService {
    override fun getCurrentDailySteps(): Int {
        return 100
    }
}