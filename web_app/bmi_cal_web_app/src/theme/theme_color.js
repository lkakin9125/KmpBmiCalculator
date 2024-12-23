import { Divider } from '@mui/material'
import kmp from 'BmiCalculator-shared'

const ColorToken = kmp.com.example.bmicalculator.common.ColorToken

const ThemeColor = {
    ContentDanger: "rgb(224,72,92)",
    ContentDangerBold: "rgb(237,118,133)",
    ContentPositive: "rgb(0,166,140)",
    ContentPositiveBold: "rgb(93,209,191)",
    ContentNormal: "rgb(255,255,255)",
    ContentPlaceHolder: "rgb(160,169,190)",
    Divider: "rgb(160,169,190)",
    ContentDisable: "rgba(160,169,190,0.5)",
    Background: "rgb(11,20,38)",
    CardBackground: "rgba(255,255,255,0.1)",
    ButtonEnableBackground: "rgb(17,153,250)",
    ButtonDisableBackground: "rgba(17,153,250,0.5)",
    Transparent:"rgba(0,0,0,0)"
}

ColorToken.prototype.toCssColor = function () {
    switch (this) {
        case ColorToken.ContentDanger: return ThemeColor.ContentDanger
        case ColorToken.ContentDangerBold: return ThemeColor.ContentDangerBold
        case ColorToken.ContentPositive: return ThemeColor.ContentPositive
        case ColorToken.ContentPositiveBold: return ThemeColor.ContentPositiveBold
        case ColorToken.ContentNormal: return ThemeColor.ContentNormal
        case ColorToken.Background: return ThemeColor.Background
        case ColorToken.CardBackground: return ThemeColor.CardBackground
        case ColorToken.ButtonEnableBackground: return ThemeColor.ButtonEnableBackground
        case ColorToken.ButtonDisableBackground: return ThemeColor.ButtonDisableBackground
    }
}

export default ThemeColor