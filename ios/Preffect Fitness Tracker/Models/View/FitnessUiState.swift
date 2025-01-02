//
//  FitnessUiState.swift
//  Preffect Fitness Tracker
//

import Foundation

struct FitnessUiState: Identifiable {
    let id: UUID
    let data: any Numeric
    let isLoading: Bool
    let isError: Bool
    let errorMessage: String
    let theme: Theme
    
    init(id: UUID = UUID(), data: any Numeric, isLoading: Bool, isError: Bool, errorMessage: String, theme: Theme) {
        self.id = id
        self.data = data
        self.isLoading = isLoading
        self.isError = isError
        self.errorMessage = errorMessage
        self.theme = theme
    }
}

extension FitnessUiState {
    static let sampleData: [FitnessUiState] = [
        FitnessUiState(
            data: 100,
            isLoading: false,
            isError: false,
            errorMessage: "",
            theme: .indigo
        ),
        FitnessUiState(
            data: 1000,
            isLoading: false,
            isError: false,
            errorMessage: "",
            theme: .teal
        ),
    ]
}
