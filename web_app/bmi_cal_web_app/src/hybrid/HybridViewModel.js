import kmp from "BmiCalculator-shared"
import { useEffect, useMemo, useState } from "react"
import { useNavigate } from "react-router";

const { HybridDi } = kmp.com.example.bmicalculator.hybrid

const useNavMenuViewModel = () => {
    const [uiState, setState] = useState(kmp.com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState)
    useEffect(() => {
        let di = new HybridDi()
        return di.subscribe((nextState)=> {
            setState(nextState)
        })
    }, [])
    return { uiState }
}

export default useNavMenuViewModel