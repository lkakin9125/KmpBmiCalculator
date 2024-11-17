//
//  NavigationMenuPageUi.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct NavigationMenuPageUi: View {
    let uiState: NavigationMenuPageUiState
    var body: some View {
        VStack{
            ForEach(uiState.buttons, id: \.self.text) { buttonUiState in
                AppFlatButton(uiState: buttonUiState)
            }
        }
        .frame(
            minWidth: 0, maxWidth: .infinity, minHeight: 0,
            maxHeight: .infinity
        )
        .padding()
        .background(ColorToken.background.getColor())
    }
}

#Preview {
    
    NavigationMenuPageUi(
        uiState: NavigationMenuPageUiState(
            buttons: NavigationMenuOption.entries.map({ option in
                AppFlatButtonUiState(text: option.displayText, enabled: true) {}
            })
                                          
                                          )
    )
}
