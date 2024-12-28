package com.ottmatt.preffectstepcounter.view

/**
 * Provides values for the user's current daily steps.
 *
 * @param currentSteps The current number of steps the user has taken today.
 * @param isLoading Whether or not we are in the process of gathering [currentSteps].
 */
data class CurrentDailyStepsUiState(
    val currentSteps: Int,
    val isLoading: Boolean
)
