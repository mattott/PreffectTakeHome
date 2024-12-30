package com.ottmatt.preffectfitnesstracker.persistence.remote.model

import kotlinx.serialization.Serializable

/**
 * { "dailyGoal": 10000 }
 */
@Serializable
data class DailyStepGoal(val dailyGoal: Int)
