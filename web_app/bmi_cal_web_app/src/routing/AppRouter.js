import kmp from "BmiCalculator-shared"
import { BrowserRouter, Route, Routes } from "react-router";
import BmiKgPage from "../bmi_calculator/kg/BmiKgPage";
import BmiPoundPage from "../bmi_calculator/pound/BmiPoundPage";
import BmiAbTestingPage from "../bmi_calculator/ab_testing/BmiAbTestingPage";
import NavMenu from "../navMenu/NavMenu";
import Hybrid from "../hybrid/Hybrid";

const NavigationMenuOption = kmp.com.example.bmicalculator.navMenu.model.NavigationMenuOption

export default () => {
    return <BrowserRouter>
        <Routes>
            <Route path="/" element={<Hybrid />} />
            <Route path="/menu" element={<NavMenu />} />
            <Route path={NavigationMenuOption.KgBmiCalculator.navPath} element={<BmiKgPage />} />
            <Route path={NavigationMenuOption.PoundBmiCalculator.navPath} element={<BmiPoundPage />} />
            <Route path={NavigationMenuOption.AbTestingBmiCalculator.navPath} element={<BmiAbTestingPage />} />
        </Routes>
    </BrowserRouter>
}
