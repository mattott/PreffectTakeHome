//
//  Preffect_Fitness_TrackerApp.swift
//  Preffect Fitness Tracker
//

import SwiftUI

@main
struct Preffect_Fitness_TrackerApp: App {
    // todo: DI inject
    private let fitnessTrackerViewModel: FitnessTrackerViewModel =
        FitnessTrackerViewModel()

    var body: some Scene {
        WindowGroup {
            FitnessTrackerView(viewModel: fitnessTrackerViewModel)
        }
    }
}
