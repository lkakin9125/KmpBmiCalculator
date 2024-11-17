//
//  BmiCalButton.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct BmiCalButton: View {
    let uiState: AppButtonUiState
    var body: some View {
        Button{
            if(uiState.enabled){
                uiState.onClick()
            }
        } label: {
            Text(uiState.text)
                .frame(maxWidth: .infinity)
            
        }
        .frame(width: .infinity)
        .buttonStyle(.bordered)
        .tint(uiState.getTintColor())
        
    }
}

extension AppButtonUiState{
    func getTintColor() -> Color{
        if(self.enabled){
            return  ColorToken.buttonenablebackground.getColor()
        }else{
            return  ColorToken.buttondisablebackground.getColor()
        }
    }
}

#Preview {
    VStack{
        BmiCalButton(
            uiState:AppButtonUiState(text: "button", enabled: true, onClick: {})
        )
        BmiCalButton(
            uiState:AppButtonUiState(text: "disable button", enabled: false, onClick: {})
        )
    }
    .padding()
    .background(ColorToken.background.getColor())
    
}
