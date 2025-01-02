//
//  Theme.swift
//  Preffect Fitness Tracker
//

import SwiftUI

enum Theme: String {
    case indigo
    case teal

    var accentColor: Color {
        switch self {
        case .teal: return .black
        case .indigo: return .white
        }
    }
    
    var mainColor: Color {
        Color(rawValue)
    }
}
