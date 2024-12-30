package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

/**
 * Provides generic values for loading the user's fitness criteria.
 *
 * @param fitnessValue The value representing the user's fitness criteria.
 * @param isLoading Whether or not we are in the process of gathering [fitnessValue].
 * @param errorMessage Description of any error that occurred while fetching [fitnessValue].
 * Will be non-empty if [isError] is true.
 * @param [isError] Whether or not an error was encountered while fetching [fitnessValue].
 */
data class FitnessUiState<T>(
    val fitnessValue: T,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isError: Boolean = false
)
