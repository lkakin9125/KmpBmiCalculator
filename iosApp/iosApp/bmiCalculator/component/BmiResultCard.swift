//
//  BmiResultCard.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared

struct BmiResultCard: View {
    let uiState: BmiResultUiState
    var body: some View {
        VStack{
            HStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/){
                Text("=").font(.footnote)
                Text(uiState.bmiResultText)
                    .font(.headline)
            }
            Text(uiState.bmiCategoryText)
                .font(.title2)
                .foregroundStyle(uiState.bmiResultTextColorToken.getColor())
        }
        .foregroundColor(ColorToken.contentnormal.getColor())
        .padding(16)
        .background(
            ColorToken.cardbackground.getColor()
                .clipShape(RoundedRectangle(cornerRadius:8))
        )
        .padding()
    }
}

#Preview {
    ScrollView(.vertical){
        VStack{
            ForEach(BmiCategory.entries,id:\.self){
                BmiResultCard(
                    uiState: BmiResultUiState(
                        bmiResultText: "\($0.minThreshold * 1.2)",
                        bmiResultTextColorToken: $0.color,
                        bmiCategoryText: $0.displayName
                    )
                )
            }
            
        }
    }
    .background(ColorToken.background.getColor())
}
