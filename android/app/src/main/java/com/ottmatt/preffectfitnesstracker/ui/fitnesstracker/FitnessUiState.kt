package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

/**
 * Provides values for the user's current daily steps.
 *
 * @param currentSteps The current number of steps the user has taken today.
 * @param isLoading Whether or not we are in the process of gathering [currentSteps].
 */
data class FitnessUiState(
    val currentSteps: Int,
    val isLoading: Boolean
)
