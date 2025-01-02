//
//  FitnessTrackerViewModel.swift
//  Preffect Fitness Tracker
//

import Combine

class FitnessTrackerViewModel: ObservableObject {
    @Published var stepCountUiState: FitnessUiState = FitnessUiState(
        data: 100,
        isLoading: false,
        isError: false,
        errorMessage: "",
        theme: .indigo
    )
     @Published var stepCountGoalUiState: FitnessUiState = FitnessUiState(
        data: 9000,
        isLoading: false,
        isError: false,
        errorMessage: "",
        theme: .teal
    )


    private var fitnessService: FitnessService = FitnessStubbedService()
    private var fitnessGoalsService: FitnessGoalsService =
        FitnessGoalsStubbedService()

    func loadFitnessLevel() {
        let stepCount = fitnessService.getStepCount()
        self.stepCountUiState = FitnessUiState(
            data: stepCount.stepCount,
            isLoading: stepCount.isLoading,
            isError: stepCount.isError,
            errorMessage: stepCount.errorMessage,
            theme: self.stepCountUiState.theme
        )
    }

    func loadFitnessGoals() {
        let stepCountGoal = fitnessGoalsService.getStepCountGoal()
        self.stepCountGoalUiState = FitnessUiState(
            data: stepCountGoal.stepCount,
            isLoading: stepCountGoal.isLoading,
            isError: stepCountGoal.isError,
            errorMessage: stepCountGoal.errorMessage,
            theme: self.stepCountGoalUiState.theme
        )
    }
}

extension FitnessTrackerViewModel {
    static var mockViewModel = FitnessTrackerViewModel()
}
