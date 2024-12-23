import "../components/"
import useBmiCalculatorViewModel from "./BmiCalculatorPoundViewModel"

export default () => {
    let { uiState } = useBmiCalculatorViewModel()
    return uiState?.toComponent()
}