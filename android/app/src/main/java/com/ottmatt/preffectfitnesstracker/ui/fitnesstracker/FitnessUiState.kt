package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

/**
 * Provides generic ui state for displaying the user's fitness.
 */
sealed interface FitnessUiState {
    /**
     * We are in the process of gathering the results.
     */
    data object Loading : FitnessUiState

    /**
     * @param message Description of any error that occurred while fetching.
     */
    data class Error(val message: String) : FitnessUiState

    /**
     * @param data The value representing the user's fitness criteria.
     */
    data class Fitness(val data: Int) : FitnessUiState
}
