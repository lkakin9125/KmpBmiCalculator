import kmp from "BmiCalculator-shared"
import { useEffect, useMemo, useState } from "react"
import { useNavigate } from "react-router";

const { BmiNavigationMenuDi } = kmp.com.example.bmicalculator.navMenu
const { NavigationMenuOption } = kmp.com.example.bmicalculator.navMenu.model

const { BmiCalculatorAbTestingDi, BmiCalculatorPoundDi, BmiCalculatorKgDi } = kmp.com.example.bmicalculator.bmiCalculator


const useNavMenuViewModel = () => {
    const [menuUiState, setMenuUiState] = useState(null)
    const [selectedNavPath, setNavPath] = useState(null)
    const [contentUiState, setContentUiState] = useState(null)

    let menuDi = useMemo(() => new BmiNavigationMenuDi(), [])
    let pageContentDiJson = useMemo(() => {
        let result = {}
        result[NavigationMenuOption.AbTestingBmiCalculator.navPath] = new BmiCalculatorAbTestingDi()
        result[NavigationMenuOption.PoundBmiCalculator.navPath] = new BmiCalculatorPoundDi()
        result[NavigationMenuOption.KgBmiCalculator.navPath] = new BmiCalculatorKgDi()
        return result
    }, [])

    useEffect(() => {
        return menuDi.subscribeUiState((nextState) => {
            setMenuUiState(nextState)
        })
    }, [menuDi])
    useEffect(() => {
        return menuDi.subscribeOnClickEvent((navEvent) => {
            if (navEvent.navPath) {
                setNavPath(navEvent.navPath)
            }
        })
    }, [menuDi])
    useEffect(() => {
        if (pageContentDiJson && pageContentDiJson[selectedNavPath]?.subscribe) {
            return pageContentDiJson[selectedNavPath].subscribe((nextState) => {
                setContentUiState(nextState)
            })
        }
    }, [selectedNavPath, pageContentDiJson])
    return { menuUiState, contentUiState }
}

export default useNavMenuViewModel