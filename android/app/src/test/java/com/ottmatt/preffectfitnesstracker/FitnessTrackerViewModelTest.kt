package com.ottmatt.preffectfitnesstracker

import android.content.res.Resources
import com.ottmatt.preffectfitnesstracker.data.DataSourceError.GenericError
import com.ottmatt.preffectfitnesstracker.data.DataSourceResult
import com.ottmatt.preffectfitnesstracker.data.local.FitnessDataSource
import com.ottmatt.preffectfitnesstracker.data.remote.FitnessGoalsDataSource
import com.ottmatt.preffectfitnesstracker.data.repository.FitnessTrackerRepository
import com.ottmatt.preffectfitnesstracker.ui.fitnesstracker.FitnessTrackerViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Tests the FitnessTrackerViewModel loading and error behavior.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class FitnessTrackerViewModelTest {
    private lateinit var viewModel: FitnessTrackerViewModel
    private lateinit var repository: FitnessTrackerRepository
    private val fitnessDataSource: FitnessDataSource = mockk(relaxed = false)
    private val fitnessGoalsDataSource: FitnessGoalsDataSource = mockk(relaxed = true)
    private val resources: Resources = mockk(relaxed = true)
    private val dataToLoadSuccess: DataSourceResult.Success<Int> = DataSourceResult.Success(5000)
    private val dataToLoadError: DataSourceResult.Error<Int> = DataSourceResult.Error(GenericError("Generic Error"))

    @Before
    fun setUp() {
        clearAllMocks()
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Show loading state while data loading in progress`() = runTest {
        coEvery { fitnessDataSource.getStepCount() } coAnswers {
            delay(1000)
            dataToLoadSuccess
        }
        repository = FitnessTrackerRepository(
            fitnessDataSource,
            fitnessGoalsDataSource,
            StandardTestDispatcher(testScheduler)
        )
        viewModel = FitnessTrackerViewModel(repository, resources)
        viewModel.loadFitnessData()
        runCurrent()

        val uiState = viewModel.stepCountUiState.value
        assertTrue(uiState.isLoading)
        assertFalse(uiState.isError)
    }

    @Test
    fun `Show content when data is loaded`() = runTest {
        coEvery { fitnessDataSource.getStepCount() } returns dataToLoadSuccess

        repository = FitnessTrackerRepository(
            fitnessDataSource,
            fitnessGoalsDataSource,
            StandardTestDispatcher(testScheduler)
        )
        viewModel = FitnessTrackerViewModel(repository, resources)
        viewModel.loadFitnessData()
        runCurrent()

        val uiState = viewModel.stepCountUiState.value
        assertEquals(dataToLoadSuccess.data, uiState.fitnessValue)
        assertFalse(uiState.isLoading)
        assertFalse(uiState.isError)
    }

    @Test
    fun `Show error state if error appeared`() = runTest {
        coEvery { fitnessDataSource.getStepCount() } returns dataToLoadError

        repository = FitnessTrackerRepository(
            fitnessDataSource,
            fitnessGoalsDataSource,
            StandardTestDispatcher(testScheduler)
        )
        viewModel = FitnessTrackerViewModel(repository, resources)
        viewModel.loadFitnessData()
        runCurrent()

        val uiState = viewModel.stepCountUiState.value
        assertTrue(uiState.isError)
        assertNotNull(uiState.errorMessage)
        assertFalse(uiState.isLoading)
    }
}