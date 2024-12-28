package com.ottmatt.preffectstepcounter.persistence.remote

class StubbedPersonalDailyGoalService: PersonalDailyGoalService {
    override fun getDailyStepsGoal(): Int {
        return 6000
    }
}