import "../components/"
import useBmiCalculatorViewModel from "./BmiCalculatorKgViewModel"

export default () => {
    let { uiState } = useBmiCalculatorViewModel()
    return uiState?.toComponent()
}