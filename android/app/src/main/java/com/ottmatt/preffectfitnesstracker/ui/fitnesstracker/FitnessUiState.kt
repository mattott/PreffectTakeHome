package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

/**
 * Provides generic values for loading the user's fitness criteria.
 *
 * @param fitnessValue The value representing the user's fitness criteria.
 * @param isLoading Whether or not we are in the process of gathering [fitnessValue].
 */
data class FitnessUiState<T>(
    val fitnessValue: T,
    val isLoading: Boolean
)
