package com.ottmatt.preffectfitnesstracker

import android.content.res.Resources
import com.ottmatt.preffectfitnesstracker.persistence.ApiResult
import com.ottmatt.preffectfitnesstracker.persistence.local.FitnessService
import com.ottmatt.preffectfitnesstracker.persistence.remote.FitnessGoalService
import com.ottmatt.preffectfitnesstracker.repository.FitnessTrackerRepository
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
    private val fitnessService: FitnessService = mockk(relaxed = false)
    private val fitnessGoalService: FitnessGoalService = mockk(relaxed = true)
    private val resources: Resources = mockk(relaxed = true)
    private val dataToLoadSuccess: ApiResult.Success<Int> = ApiResult.Success(5000)
    private val dataToLoadError: ApiResult.Error<Int> = ApiResult.Error()

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
        coEvery { fitnessService.getStepCount() } coAnswers {
            delay(1000)
            dataToLoadSuccess
        }
        repository = FitnessTrackerRepository(
            fitnessService,
            fitnessGoalService,
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
        coEvery { fitnessService.getStepCount() } returns dataToLoadSuccess

        repository = FitnessTrackerRepository(
            fitnessService,
            fitnessGoalService,
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
        coEvery { fitnessService.getStepCount() } returns dataToLoadError

        repository = FitnessTrackerRepository(
            fitnessService,
            fitnessGoalService,
            StandardTestDispatcher(testScheduler)
        )
        viewModel = FitnessTrackerViewModel(repository, resources)
        viewModel.loadFitnessData()
        runCurrent()

        val uiState = viewModel.stepCountUiState.value
        assertTrue(uiState.isError)
        assertFalse(uiState.isLoading)
    }
}