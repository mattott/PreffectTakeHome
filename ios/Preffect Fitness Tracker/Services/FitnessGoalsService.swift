//
//  FitnessGoalsService.swift
//  Preffect Fitness Tracker
//

protocol FitnessGoalsService {
    func getStepCountGoal() -> FitnessGoal
}

/**
 * A production implementation of this would make 
 */
class FitnessGoalsStubbedService: FitnessGoalsService {
    func getStepCountGoal() -> FitnessGoal {
        return FitnessGoal(
            stepCount: 9999,
            isLoading: false,
            isError: false,
            errorMessage: ""
        )
    }
}
