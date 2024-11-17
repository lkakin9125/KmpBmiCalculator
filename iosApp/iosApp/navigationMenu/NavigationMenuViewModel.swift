//
//  NavigationMenuViewModel.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

class NavigationMenuViewModel : ObservableObject{
    private let di = BmiNavigationMenuDi()
    @Published var uiState: NavigationMenuPageUiState? = nil
    @Published var navigationMenuPath: NavigationPath = NavigationPath()
    var cancelFuncs: [()->Void] = []
    
    init(){
        cancelFuncs = [
            di.subscribeUiState { nextUiState in
                self.uiState = nextUiState
            },
            di.subscribeOnClickEvent { option in
                self.navigationMenuPath.append(option)
            }
        ]
    }
    
    deinit{
        cancelFuncs.forEach { cancelFunc in
            cancelFunc()
        }
    }
}

