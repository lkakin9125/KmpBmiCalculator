import kmp from "BmiCalculator-shared"
import { useEffect, useMemo, useState } from "react"
import { useNavigate } from "react-router";

const { BmiNavigationMenuDi } = kmp.com.example.bmicalculator.navMenu

const useNavMenuViewModel = () => {
    const [uiState, setState] = useState(null)
    const navigate = useNavigate();

    let di = useMemo(() => new BmiNavigationMenuDi(), [])
    useEffect(() => {
        return di.subscribeUiState((nextState) => {
            setState(nextState)
        })
    }, [di])
    useEffect(() => {
        return di.subscribeOnClickEvent((navEvent) => {
            if (navEvent.navPath) {
                navigate(navEvent.navPath)
            }
        })
    }, [di])
    return { uiState }
}

export default useNavMenuViewModel