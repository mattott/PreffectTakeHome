package com.ottmatt.preffectfitnesstracker.persistence.local

import com.ottmatt.preffectfitnesstracker.persistence.ApiResult
import kotlinx.coroutines.delay

/**
 * A stubbed version of the Google Fit API. Since the Google Fit API is deprecated and will terminate
 * June 30 2025, we'd need to migrate to the Health Connect API. However, this shouldn't be too big
 * of a problem using the FitnessService API as a shim between the actual data provider.
 */
class StubbedFitnessService : FitnessService {
    override suspend fun getStepCount(): ApiResult<Int> {
        delay(2000) // sleep to emulate data retrieval from health connect api.
        return ApiResult.Success(5300)
    }
}