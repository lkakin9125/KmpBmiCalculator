//
//  BmiCalculatorLoadingPage.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

struct BmiCalculatorLoadingPage: View {
    var body: some View {
        VStack{
            Spacer()
            ProgressView()
                .progressViewStyle(.circular)
            Spacer()
        }
        .frame(
                    maxWidth: .infinity,
                    maxHeight: .infinity,
                    alignment: .topLeading
                )
        .background(ColorToken.background.getColor())
    }
}

#Preview {
    BmiCalculatorLoadingPage()
}
