//
//  BmiCalculatorContentPage.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct BmiCalculatorContentPage: View {
    let uiState: BmiPageContentUiState
    var body: some View {
        VStack{
            BmiInputView(uiState: uiState.heightInputUiState)
            BmiInputView(uiState: uiState.weightInputUiState)
            if(uiState.resultUiState != nil){
                BmiResultCard(uiState: uiState.resultUiState!)
            }
            Spacer()
            BmiCalButton(uiState: uiState.buttonUiState)
                .padding()
        }
        .frame(
            height: .infinity,
            alignment: .topLeading
        )
        .background(ColorToken.background.getColor())
        .navigationTitle(uiState.title)
        .toolbar {
            ToolbarItem(placement: .principal) {
                Text(uiState.title)
                    .foregroundColor(ColorToken.contentnormal.getColor())
            }
        }
    }
}

#Preview {
    BmiCalculatorContentPage(
        uiState:BmiPageContentUiState(
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
