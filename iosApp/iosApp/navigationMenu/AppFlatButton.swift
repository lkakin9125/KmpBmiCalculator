//
//  AppFlatButton.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct AppFlatButton: View {
    let uiState:AppFlatButtonUiState
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
    }
}

#Preview {
    AppFlatButton(
        uiState: AppFlatButtonUiState(text: "Button", enabled: true, onClick: {})
    )
}
