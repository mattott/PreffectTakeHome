package com.ottmatt.preffectstepcounter.view

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FrontPageViewModel : ViewModel() {
    // todo: populate with data from google fit.
    private val _currentDailyStepsUiState = MutableStateFlow(CurrentDailyStepsUiState(100, false))
    val currentDailyStepsUiState: StateFlow<CurrentDailyStepsUiState> = _currentDailyStepsUiState.asStateFlow()

    // todo: populate with data from server.
    private val _personalDailyGoalUiState = MutableStateFlow(PersonalDailyGoalUiState(6000, false))
    val personalDailyGoalUiState: StateFlow<PersonalDailyGoalUiState> = _personalDailyGoalUiState.asStateFlow()
}