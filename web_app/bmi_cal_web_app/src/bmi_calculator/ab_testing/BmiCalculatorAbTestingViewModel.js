import kmp from "BmiCalculator-shared"
import { useEffect, useState } from "react"

const { BmiCalculatorAbTestingDi } = kmp.com.example.bmicalculator.bmiCalculator


const useBmiCalculatorViewModel = () => {
    const [uiState, setState] = useState(kmp.com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState)
    useEffect(() => {
        let di = new BmiCalculatorAbTestingDi()
        return di.subscribe((nextState)=> {
            console.log("abtesting state", nextState)
            setState(nextState)
        })
    }, [])
    return { uiState }
}

export default useBmiCalculatorViewModel