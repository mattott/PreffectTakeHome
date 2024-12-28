package com.ottmatt.preffectstepcounter.repository

import com.ottmatt.preffectstepcounter.persistence.local.DailyStepsService
import com.ottmatt.preffectstepcounter.persistence.remote.PersonalDailyGoalService
import javax.inject.Inject

class FrontPageRepository @Inject constructor(
    private val dailyStepsService: DailyStepsService,
    private val personalDailyGoalService: PersonalDailyGoalService
) {
    suspend fun getCurrentDailySteps(): Int {
        return dailyStepsService.getCurrentDailySteps()
    }

    suspend fun getPersonalDailyStepsGoal(): Int {
        return personalDailyGoalService.getDailyStepsGoal()
    }
}
