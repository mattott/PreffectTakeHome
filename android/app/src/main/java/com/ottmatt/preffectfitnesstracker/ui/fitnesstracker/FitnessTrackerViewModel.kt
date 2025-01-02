package com.ottmatt.preffectfitnesstracker.ui.fitnesstracker

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ottmatt.preffectfitnesstracker.R
import com.ottmatt.preffectfitnesstracker.data.DataSourceError
import com.ottmatt.preffectfitnesstracker.data.DataSourceResult
import com.ottmatt.preffectfitnesstracker.data.remote.RemoteDataSourceError
import com.ottmatt.preffectfitnesstracker.data.repository.FitnessTrackerRepository
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
    private val _stepCountUiState = MutableStateFlow<FitnessUiState>(FitnessUiState.Loading)
    val stepCountUiState: StateFlow<FitnessUiState> =
        _stepCountUiState.asStateFlow()

    private val _stepCountGoalUiState = MutableStateFlow<FitnessUiState>(FitnessUiState.Loading)
    val stepCountGoalUiState: StateFlow<FitnessUiState> =
        _stepCountGoalUiState.asStateFlow()

    /**
     * Load user fitness data, updating loading and error states as necessary.
     */
    fun loadFitnessData() {
        _stepCountUiState.update { _ -> FitnessUiState.Loading }
        viewModelScope.launch {
            val stepCountResult = fitnessTrackerRepository.getStepCount()
            _stepCountUiState.update { _ -> stepCountResult.getStepCountFitnessUiState() }
        }
    }

    /**
     * Load user fitness goals, updating loading and error states as necessary.
     */
    fun loadFitnessGoals() {
        _stepCountGoalUiState.update { _ -> FitnessUiState.Loading }
        viewModelScope.launch {
            val stepCountGoalResult = fitnessTrackerRepository.getStepCountGoal()
            _stepCountGoalUiState.update { _ -> stepCountGoalResult.getStepCountGoalFitnessUiState() }
        }
    }

    private fun DataSourceResult<Int>.getStepCountFitnessUiState(): FitnessUiState {
        return when (this) {
            is DataSourceResult.Error -> FitnessUiState.Error(error.localErrorMessage)
            is DataSourceResult.Success -> FitnessUiState.Fitness(data ?: 0)
        }
    }

    private fun DataSourceResult<Int>.getStepCountGoalFitnessUiState(): FitnessUiState {
        return when (this) {
            is DataSourceResult.Error -> FitnessUiState.Error(error.remoteErrorMessage)
            is DataSourceResult.Success -> FitnessUiState.Fitness(data ?: 0)
        }
    }

    @Suppress("UnusedReceiverParameter")
    private val DataSourceError?.localErrorMessage: String
        get() = resources.getString(R.string.error_local_generic)

    private val DataSourceError?.remoteErrorMessage: String
        get() {
            val errorResId = when (this) {
                is RemoteDataSourceError.RedirectError -> R.string.error_remote_redirect
                is RemoteDataSourceError.ClientError -> R.string.error_remote_client
                is RemoteDataSourceError.ServerError -> R.string.error_remote_server
                else -> R.string.error_remote_generic
            }
            return resources.getString(errorResId)
        }
}