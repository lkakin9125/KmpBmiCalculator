//
//  KgBmiCalculator.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct KgBmiCalculator: View {
    @StateObject var viewModel = KgBmiCalculatorViewModel()
    var body: some View {
        BmiCalculatorPage(uiState: viewModel.uiState)
    }
}
