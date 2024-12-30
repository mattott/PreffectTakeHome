package com.ottmatt.preffectfitnesstracker.persistence.remote

import com.ottmatt.preffectfitnesstracker.persistence.DataSourceResult
import com.ottmatt.preffectfitnesstracker.persistence.remote.model.DailyStepGoal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class FitnessGoalsNetworkDataSource @Inject constructor(
    private val httpClient: HttpClient
) : FitnessGoalsDataSource {
    override suspend fun getStepCountGoal(): DataSourceResult<Int> {
        return try {
            val stepCountGoal = httpClient.get("goals/step_count.json")
                .body<DailyStepGoal>()
                .dailyGoal
            DataSourceResult.Success(stepCountGoal)
        } catch (e: Exception) {
            DataSourceResult.Error()
        }
    }
}
