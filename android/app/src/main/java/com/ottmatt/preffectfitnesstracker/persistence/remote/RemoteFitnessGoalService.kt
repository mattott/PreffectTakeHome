package com.ottmatt.preffectfitnesstracker.persistence.remote

import com.ottmatt.preffectfitnesstracker.persistence.ApiResult
import com.ottmatt.preffectfitnesstracker.persistence.remote.model.DailyStepGoal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RemoteFitnessGoalService @Inject constructor(
    private val httpClient: HttpClient
) : FitnessGoalService {
    override suspend fun getStepCountGoal(): ApiResult<Int> {
        return try {
            val stepCountGoal = httpClient.get("goals/step_count.json")
                .body<DailyStepGoal>()
                .dailyGoal
            ApiResult.Success(stepCountGoal)
        } catch (e: Exception) {
            ApiResult.Error()
        }
    }
}

