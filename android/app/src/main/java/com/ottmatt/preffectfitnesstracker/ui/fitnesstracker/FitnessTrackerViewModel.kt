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
    // todo: populate with data from google fit.
    private val _fitnessUiState = MutableStateFlow(FitnessUiState(100, false))
    val fitnessUiState: StateFlow<FitnessUiState<Int>> =
        _fitnessUiState.asStateFlow()

    // todo: populate with data from server.
    private val _fitnessGoalUiState = MutableStateFlow(FitnessUiState(6000, false))
    val fitnessGoalUiState: StateFlow<FitnessUiState<Int>> =
        _fitnessGoalUiState.asStateFlow()

    fun loadCurrentFitness() {
        // how do we handle the case where we are waiting for results?
        // this was something that came up during the 2nd interview and something that I probably
        // need to have solved for.
        _fitnessUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCount = fitnessTrackerRepository.getStepCount()
            _fitnessUiState.update { state ->
                state.copy(
                    fitnessValue = stepCount,
                    isLoading = false
                )
            }
        }
    }

    fun loadFitnessGoal() {
        // if this takes too long to fetch, then we need to make the request in a Service.
        _fitnessGoalUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCountGoal = fitnessTrackerRepository.getStepCountGoal()
            _fitnessGoalUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountGoal,
                    isLoading = false
                )
            }
        }
    }
}