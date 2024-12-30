package com.ottmatt.preffectfitnesstracker.persistence.remote

import com.ottmatt.preffectfitnesstracker.persistence.DataSourceError.GenericError
import com.ottmatt.preffectfitnesstracker.persistence.DataSourceResult
import com.ottmatt.preffectfitnesstracker.persistence.remote.RemoteDataSourceError.ClientError
import com.ottmatt.preffectfitnesstracker.persistence.remote.RemoteDataSourceError.RedirectError
import com.ottmatt.preffectfitnesstracker.persistence.remote.RemoteDataSourceError.ServerError
import com.ottmatt.preffectfitnesstracker.persistence.remote.model.DailyStepGoal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
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
        } catch (e: RedirectResponseException) { // 3xx response
            DataSourceResult.Error(RedirectError(e.message))
        } catch (e: ClientRequestException) { // 4xx response
            DataSourceResult.Error(ClientError(e.message))
        } catch (e: ServerResponseException) { // 5xx response
            DataSourceResult.Error(ServerError(e.message))
        } catch (e: Exception) {
            DataSourceResult.Error(GenericError(e.message ?: ""))
        }
    }
}
