//
//  BmiInputView.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct BmiInputView : View {
    let uiState: BmiInputUiState
    var body: some View {
        let inputBinding = Binding<String>(get: {uiState.inputText}, set: {uiState.onInputChanged($0)})
        VStack(alignment: .leading) {
            HStack(alignment: .center){
                TextField(
                    "",
                    text: inputBinding,
                    prompt: Text(uiState.placeholder)
                        .foregroundColor(.white.opacity(0.5))
                )
            
                Text(uiState.unitText)
            }
            .padding(16)
            .background(
                ColorToken.cardbackground.getColor()
                    .clipShape(RoundedRectangle(cornerRadius:8))
            )
            .foregroundColor(ColorToken.contentnormal.getColor())
            
            Text(uiState.errorText)
                .font(.footnote)
                .foregroundStyle(.red)
        }
        .padding()
    }
}
#Preview {
    VStack{
        BmiInputView(uiState: BmiInputUiState(
            inputText: "",
            unitText: "Kg",
            placeholder: "Please enter Your Weight",
            errorText: "",
            onInputChanged: {_ in }
        ))
    }
    .background(
        ColorToken.background.getColor()
    )
}

#Preview {
    VStack{
        BmiInputView(uiState: BmiInputUiState(
            inputText: "",
            unitText: "Kg",
            placeholder: "Please enter Your Weight",
            errorText: "Invalid Input",
            onInputChanged: {_ in }
        ))
    }
    .background(
        ColorToken.background.getColor()
    )
}
