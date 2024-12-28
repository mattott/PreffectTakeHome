package com.ottmatt.preffectstepcounter.ui.fitnesstracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottmatt.preffectstepcounter.repository.FitnessTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FitnessTrackerViewModel @Inject constructor(
    private val fitnessTrackerRepository: FitnessTrackerRepository
) : ViewModel() {
    // todo: populate with data from google fit.
    private val _fitnessUiState = MutableStateFlow(FitnessUiState(100, false))
    val fitnessUiState: StateFlow<FitnessUiState> =
        _fitnessUiState.asStateFlow()

    // todo: populate with data from server.
    private val _fitnessGoalUiState = MutableStateFlow(FitnessGoalUiState(6000, false))
    val fitnessGoalUiState: StateFlow<FitnessGoalUiState> =
        _fitnessGoalUiState.asStateFlow()

    fun getCurrentDailySteps() {
        // how do we handle the case where we are waiting for results?
        // this was something that came up during the 2nd interview and something that I probably
        // need to have solved for.
        viewModelScope.launch {
            fitnessTrackerRepository.getCurrentDailySteps()
        }
    }

    fun getPersonalDailyGoal() {
        // if this takes too long to fetch, then we need to make the request in a Service.
        viewModelScope.launch {
            fitnessTrackerRepository.getPersonalDailyStepsGoal()
        }
    }
}