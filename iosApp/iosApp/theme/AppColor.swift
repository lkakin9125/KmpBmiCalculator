//
//  AppColor.swift
//  iosApp
//
//  Created by Kakin Lau on 17/11/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

extension ColorToken{
    func getColor() -> Color{
        return switch(self){
        case ColorToken.background: Color(hex: 0x0b1426)
        case ColorToken.buttondisablebackground: Color(hex:0x1199FA,alpha: 0.5)
        case ColorToken.buttonenablebackground:Color(hex:0x1199FA)
        case ColorToken.contentdanger: Color(hex:0xE0485C)
        case ColorToken.contentdangerbold: Color(hex:0xED7685)
        case ColorToken.contentpositive: Color(hex: 0x00A68C)
        case ColorToken.contentpositivebold: Color(hex: 0x5DD1BF)
        case ColorToken.contentnormal: Color(hex: 0xFFFFFF)
        case ColorToken.cardbackground: Color(.sRGB,red: 1,green: 1,blue: 1,opacity: 0.1)
        default: Color(.sRGB,red: 1,green: 1,blue: 1)
        }
    }
}

extension Color {
    init(hex: UInt, alpha: Double = 1) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}
