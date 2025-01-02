//
//  FitnessService.swift
//  Preffect Fitness Tracker
//

protocol FitnessService {
    func getStepCount() -> FitnessLevel
}

/**
 * An implementation would make calls to the Apple HealthKit API
 */
class FitnessStubbedService: FitnessService {
    func getStepCount() -> FitnessLevel {
        return FitnessLevel(
            stepCount: 100,
            isLoading: false,
            isError: false,
            errorMessage: ""
        )
    }
}
