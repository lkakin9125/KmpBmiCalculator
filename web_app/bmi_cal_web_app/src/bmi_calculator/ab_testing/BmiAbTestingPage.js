import "../components/"
import useBmiCalculatorViewModel from "./BmiCalculatorAbTestingViewModel"

export default () => {
    let { uiState } = useBmiCalculatorViewModel()
    return uiState?.toComponent()
}