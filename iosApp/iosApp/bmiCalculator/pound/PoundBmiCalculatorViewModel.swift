//
//  PoundBmiCalculatorViewModel.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

class PoundBmiCalculatorViewModel: ObservableObject{
    private let di = BmiCalculatorPoundDi()
    @Published var uiState: BmiPageUiState? = nil
    var cancelFunc: (()-> Void)? = nil
    init(){
        cancelFunc = di.subscribe { nextState in
            self.uiState = nextState
        }
    }
    
    deinit{
        cancelFunc?()
    }
}

