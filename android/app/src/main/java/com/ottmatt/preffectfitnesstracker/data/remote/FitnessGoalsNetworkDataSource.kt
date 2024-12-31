package com.ottmatt.preffectfitnesstracker.data.remote

import com.ottmatt.preffectfitnesstracker.data.DataSourceError.GenericError
import com.ottmatt.preffectfitnesstracker.data.DataSourceResult
import com.ottmatt.preffectfitnesstracker.data.remote.RemoteDataSourceError.ClientError
import com.ottmatt.preffectfitnesstracker.data.remote.RemoteDataSourceError.RedirectError
import com.ottmatt.preffectfitnesstracker.data.remote.RemoteDataSourceError.ServerError
import com.ottmatt.preffectfitnesstracker.data.remote.model.DailyStepGoal
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
