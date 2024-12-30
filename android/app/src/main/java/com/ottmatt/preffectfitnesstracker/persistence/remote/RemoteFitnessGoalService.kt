package com.ottmatt.preffectfitnesstracker.persistence.remote

import com.ottmatt.preffectfitnesstracker.persistence.remote.model.DailyStepGoal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteFitnessGoalService @Inject constructor(
    private val httpClient: HttpClient
) : FitnessGoalService {
    override suspend fun getStepCountGoal(): Int {
        val stepGoal = try {
            val dailyStepGoal = httpClient.get("goals/step_count.json").body<DailyStepGoal>()
            dailyStepGoal.dailyGoal
        } catch (e: Exception) {
            0
        }
        return stepGoal
    }
}

