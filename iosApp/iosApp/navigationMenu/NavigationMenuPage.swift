//
//  NavigationMenuPage.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct NavigationMenuPage: View {
    @StateObject var viewModel = NavigationMenuViewModel()
    var body: some View {
        NavigationStack(path: $viewModel.navigationMenuPath){
            switch(viewModel.uiState){
            case let mState as NavigationMenuPageUiState: NavigationMenuPageUi(uiState: mState)
                    .navigationDestination(for: NavigationMenuOption.self) { nav in
                        switch(nav){
                        case .abtestingbmicalculator: AbTestingBmiCalculator()
                        case .kgbmicalculator: KgBmiCalculator()
                        case .poundbmicalculator: PoundBmiCalculator()
                        default: EmptyView()
                        }
                    }
//                    .navigationDestination(for: MenuNavigation.self) { nav in
//                        switch(nav){
//                        case .bmiabtestingcalculator: BmiAbTestingPage()
//                        case .bmicalculator: BmiCalculatorPage()
//                        case .bmiinpoundcalculator: BmiPoundPage()
//                        case .language: LangugePage()
//                        default: EmptyView()
//                        }
//                    }
                
            default: EmptyView()
            }
            
        }
    }
}
