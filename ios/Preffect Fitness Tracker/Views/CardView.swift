//
//  CardView.swift
//  Preffect Fitness Tracker
//

import SwiftUI

struct CardView: View {
    let title: String
    let fitness: FitnessUiState
    let theme: Theme

    var body: some View {
        VStack(alignment: .center) {
            Spacer()
            Text(title)
                .font(.headline)
                .padding(.bottom, 10)
                .accessibilityAddTraits(.isHeader)
            Text("\(fitness.data)")
                .font(.caption)
                .accessibilityLabel("\(fitness.data)")
            Spacer()
        }
        .padding()
        .foregroundColor(theme.accentColor)
    }
}

struct CardView_Previews: PreviewProvider {
    static var fitness = FitnessUiState.sampleData[0]
    static var previews: some View {
        CardView(title: "Today's Steps", fitness: fitness, theme: .indigo)
            .preferredColorScheme(.light)
            .background(fitness.theme.mainColor)
            .previewLayout(.fixed(width: 400, height: 60))
    }
}
