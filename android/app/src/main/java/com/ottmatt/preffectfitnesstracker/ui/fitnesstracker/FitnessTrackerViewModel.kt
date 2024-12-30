package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottmatt.preffectfitnesstracker.R
import com.ottmatt.preffectfitnesstracker.persistence.DataSourceError
import com.ottmatt.preffectfitnesstracker.persistence.DataSourceResult
import com.ottmatt.preffectfitnesstracker.persistence.remote.RemoteDataSourceError
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
     * Load user fitness data, updating loading and error states as necessary.
     */
    fun loadFitnessData() {
        _stepCountUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCountResult = fitnessTrackerRepository.getStepCount()
            _stepCountUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountResult.data ?: 0,
                    isLoading = false,
                    errorMessage = getLocalErrorMessage(stepCountResult.error),
                    isError = stepCountResult is DataSourceResult.Error
                )
            }
        }
    }

    /**
     * Load user fitness goals, updating loading and error states as necessary.
     */
    fun loadFitnessGoals() {
        _stepCountGoalUiState.update { state -> state.copy(isLoading = true) }
        viewModelScope.launch {
            val stepCountGoalResult = fitnessTrackerRepository.getStepCountGoal()
            _stepCountGoalUiState.update { state ->
                state.copy(
                    fitnessValue = stepCountGoalResult.data ?: 0,
                    isLoading = false,
                    errorMessage = getRemoteErrorMessage(stepCountGoalResult.error),
                    isError = stepCountGoalResult is DataSourceResult.Error
                )
            }
        }
    }

    private fun getLocalErrorMessage(error: DataSourceError?): String {
        return resources.getString(R.string.error_local_generic)
    }

    private fun getRemoteErrorMessage(error: DataSourceError?): String {
        val errorResId = when (error) {
            is RemoteDataSourceError.RedirectError -> R.string.error_remote_redirect
            is RemoteDataSourceError.ClientError -> R.string.error_remote_client
            is RemoteDataSourceError.ServerError -> R.string.error_remote_server
            else -> R.string.error_remote_generic
        }
        return resources.getString(errorResId)
    }
}