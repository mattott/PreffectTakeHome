package com.ottmatt.preffectstepcounter.view

/**
 * Provides values for the user's daily goals.
 *
 * @param stepsInGoal The total number of steps the user has has set as their personal daily goal.
 * @param isLoading Whether or not we are in the process of gathering [stepsInGoal].
 */
data class PersonalDailyGoalUiState(
    val stepsInGoal: Int,
    val isLoading: Boolean
)
