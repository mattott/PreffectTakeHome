//
//  FitnessTrackerView.swift
//  Preffect Fitness Tracker
//

import SwiftUI

struct FitnessTrackerView: View {
    @ObservedObject var viewModel: FitnessTrackerViewModel

    var body: some View {
        
        VStack(alignment: .center, spacing: 10) {
            Spacer()
            CardView(
                title: "Today's Step Count",
                fitness: viewModel.stepCountUiState,
                theme: .indigo
            )
            .background(Theme.indigo.mainColor)
            .cornerRadius(20)
            .shadow(color: Color.black.opacity(0.4), radius: 12, x: 6, y: 6)
            .scaledToFit()
            CardView(
                title: "Your daily step goal",
                fitness: viewModel.stepCountGoalUiState,
                theme: .teal
            )
            .background(Theme.teal.mainColor)
            .cornerRadius(20)
            .shadow(color: Color.black.opacity(0.4), radius: 12, x: 6, y: 6)
            .scaledToFit()

            Spacer()
        }
        .padding()
    }
}

struct FitnessTrackerView_Previews: PreviewProvider {
    static var previews: some View {
        FitnessTrackerView(
            viewModel: FitnessTrackerViewModel.mockViewModel
        )
    }
}
