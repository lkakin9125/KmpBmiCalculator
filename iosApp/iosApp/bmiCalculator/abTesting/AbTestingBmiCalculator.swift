//
//  AbTestingBmiCalculator.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct AbTestingBmiCalculator: View {
    @StateObject var viewModel = AbTestingBmiCalculatorViewModel()
    var body: some View {
        BmiCalculatorPage(uiState: viewModel.uiState)
    }
}

