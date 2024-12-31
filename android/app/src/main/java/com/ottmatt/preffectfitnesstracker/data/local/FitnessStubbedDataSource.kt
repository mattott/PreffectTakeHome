package com.ottmatt.preffectfitnesstracker.data.local

import com.ottmatt.preffectfitnesstracker.data.DataSourceResult
import kotlinx.coroutines.delay

/**
 * A stubbed version of the Google Fit API. Since the Google Fit API is deprecated and will terminate
 * June 30 2025, we'd need to migrate to the Health Connect API. However, this shouldn't be too big
 * of a problem using the FitnessService API as a shim between the actual data provider.
 */
class FitnessStubbedDataSource : FitnessDataSource {
    override suspend fun getStepCount(): DataSourceResult<Int> {
        delay(2000) // sleep to emulate data retrieval from health connect api.
        return DataSourceResult.Success(5300)
    }
}