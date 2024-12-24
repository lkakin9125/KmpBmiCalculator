import useViewModel from "./HybridViewModel"
import "../navMenu/NavMenuPage"
import "../bmi_calculator/components"
import ThemeColor from "../theme/theme_color"
import "./HybridPage"

export default () => {
    let { uiState } = useViewModel()
    console.log("hybrid uistate", uiState)
    return uiState?.toComponent()
}