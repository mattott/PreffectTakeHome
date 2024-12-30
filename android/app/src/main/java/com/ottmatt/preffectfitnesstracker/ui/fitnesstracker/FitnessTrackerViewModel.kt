package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val fitnessTrackerRepository: FitnessTrackerRepository
) : ViewModel() {
    private val _stepCountUiState = MutableStateFlow(FitnessUiState(100, false))
    val stepCountUiState: StateFlow<FitnessUiState<Int>> =
        _stepCountUiState.asStateFlow()

    private val _stepCountGoalUiState = MutableStateFlow(FitnessUiState(6000, false))
    val stepCountGoalUiState: StateFlow<FitnessUiState<Int>> =
        _stepCountGoalUiState.asStateFlow()

    /**
     * Fetch and update user fitness data.
     */
    fun loadFitnessData() {
        _stepCountUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCount = fitnessTrackerRepository.getStepCount()
            _stepCountUiState.update { state ->
                state.copy(
                    fitnessValue = stepCount,
                    isLoading = false
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
            val stepCountGoal = fitnessTrackerRepository.getStepCountGoal()
            _stepCountGoalUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountGoal,
                    isLoading = false
                )
            }
        }
    }
}