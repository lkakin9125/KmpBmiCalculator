//
//  PoundBmiCalculator.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct PoundBmiCalculator: View {
    @StateObject var viewModel = PoundBmiCalculatorViewModel()
    var body: some View {
        BmiCalculatorPage(uiState: viewModel.uiState)
    }
}
