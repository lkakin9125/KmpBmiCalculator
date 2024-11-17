//
//  BmiCalculatorPage.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct BmiCalculatorPage: View {
    let uiState: BmiPageUiState?
    var body: some View {
        switch(uiState){
        case let mUiState as BmiPageContentUiState : BmiCalculatorContentPage(uiState: mUiState)
        default: BmiCalculatorLoadingPage()
        }
        
    }
}

#Preview {
    BmiCalculatorPage(
        uiState: BmiPageContentUiState(
            title: "BmiCalculator",
            heightInputUiState: BmiInputUiState(
                inputText: "",
                unitText: "m",
                placeholder: "Please enter Your Height",
                errorText: "",
                onInputChanged: {_ in }
            )
            , weightInputUiState: BmiInputUiState(
                inputText: "",
                unitText: "Kg",
                placeholder: "Please enter Your Weight",
                errorText: "",
                onInputChanged: {_ in }
            ), resultUiState:BmiResultUiState(
                bmiResultText: "\(BmiCategory.normalrange.minThreshold * 1.2)",
                bmiResultTextColorToken: BmiCategory.normalrange.color,
                bmiCategoryText: BmiCategory.normalrange.displayName
            ), buttonUiState: AppButtonUiState(text: "submit", enabled: true, onClick: {})
        )
    )
}


#Preview {
    BmiCalculatorPage(
        uiState: BmiPageLoadingUiState()
    )
}

#Preview {
    BmiCalculatorPage(
        uiState: nil
    )
}
