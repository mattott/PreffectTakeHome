package com.ottmatt.preffectstepcounter.persistence.local

class StubbedDailyStepsService: DailyStepsService {
    override fun getCurrentDailySteps(): Int {
        return 100
    }
}