package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottmatt.preffectfitnesstracker.R
import com.ottmatt.preffectfitnesstracker.persistence.ApiResult
import com.ottmatt.preffectfitnesstracker.repository.FitnessTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitnessTrackerViewModel @Inject constructor(
    private val fitnessTrackerRepository: FitnessTrackerRepository,
    private val resources: Resources
) : ViewModel() {
    private val _stepCountUiState = MutableStateFlow(FitnessUiState(100))
    val stepCountUiState: StateFlow<FitnessUiState<Int>> =
        _stepCountUiState.asStateFlow()

    private val _stepCountGoalUiState = MutableStateFlow(FitnessUiState(6000))
    val stepCountGoalUiState: StateFlow<FitnessUiState<Int>> =
        _stepCountGoalUiState.asStateFlow()

    /**
     * Fetch and update user fitness data.
     */
    fun loadFitnessData() {
        _stepCountUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCountResult = fitnessTrackerRepository.getStepCount()
            _stepCountUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountResult.data ?: 0,
                    isLoading = false,
                    errorMessage = resources.getString(R.string.error_step_count),
                    isError = stepCountResult is ApiResult.Error
                )
            }
        }
    }

    /**
     * Fetch and update user fitness goals.
     */
    fun loadFitnessGoals() {
        _stepCountGoalUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCountGoalResult = fitnessTrackerRepository.getStepCountGoal()
            _stepCountGoalUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountGoalResult.data ?: 0,
                    isLoading = false,
                    errorMessage = resources.getString(R.string.error_step_goal),
                    isError = stepCountGoalResult is ApiResult.Error
                )
            }
        }
    }
}