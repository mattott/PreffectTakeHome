package com.ottmatt.preffectstepcounter.persistence.local

interface DailyStepsService {
    fun getCurrentDailySteps(): Int
}