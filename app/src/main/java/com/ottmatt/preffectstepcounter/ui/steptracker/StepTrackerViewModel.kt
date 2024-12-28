package com.ottmatt.preffectstepcounter.ui.steptracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottmatt.preffectstepcounter.repository.StepTrackerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StepTrackerViewModel @Inject constructor(
    private val stepTrackerRepository: StepTrackerRepository
) : ViewModel() {
    // todo: populate with data from google fit.
    private val _currentDailyStepsUiState = MutableStateFlow(CurrentDailyStepsUiState(100, false))
    val currentDailyStepsUiState: StateFlow<CurrentDailyStepsUiState> =
        _currentDailyStepsUiState.asStateFlow()

    // todo: populate with data from server.
    private val _personalDailyGoalUiState = MutableStateFlow(PersonalDailyGoalUiState(6000, false))
    val personalDailyGoalUiState: StateFlow<PersonalDailyGoalUiState> =
        _personalDailyGoalUiState.asStateFlow()

    fun getCurrentDailySteps() {
        // how do we handle the case where we are waiting for results?
        // this was something that came up during the 2nd interview and something that I probably
        // need to have solved for.
        viewModelScope.launch {
            stepTrackerRepository.getCurrentDailySteps()
        }
    }

    fun getPersonalDailyGoal() {
        // if this takes too long to fetch, then we need to make the request in a Service.
        viewModelScope.launch {
            stepTrackerRepository.getPersonalDailyStepsGoal()
        }
    }
}