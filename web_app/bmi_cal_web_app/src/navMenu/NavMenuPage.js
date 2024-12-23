import kmp from "BmiCalculator-shared"
import "../common"
import ThemeColor from "../theme/theme_color"

kmp.com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState.prototype.toComponent = function () {
    return <div
        style={{
            display: "flex",
            flexDirection: "column",
            height: "100vh",
            width: "100%",
            background: ThemeColor.Background,
        }}>
        <div style={{ flex: 1 }}></div>
        {
            this.buttons.toArray().map((btnUiState) => {
                return btnUiState.toComponent()
            })
        }
        <div style={{ flex: 1 }}></div>
    </div>
}