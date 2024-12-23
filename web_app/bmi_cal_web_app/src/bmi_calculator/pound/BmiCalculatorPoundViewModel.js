import kmp from "BmiCalculator-shared"
import { useEffect, useState } from "react"

const { BmiCalculatorPoundDi } = kmp.com.example.bmicalculator.bmiCalculator


const useBmiCalculatorViewModel = () => {
    const [uiState, setState] = useState(kmp.com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState)
    useEffect(() => {
        let di = new BmiCalculatorPoundDi()
        return di.subscribe((nextState)=> {
            setState(nextState)
        })
    }, [])
    return { uiState }
}

export default useBmiCalculatorViewModel