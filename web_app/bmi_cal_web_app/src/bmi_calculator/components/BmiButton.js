import { Button } from "@mui/material"
import kmp from "BmiCalculator-shared"
import ThemeColor from "../../theme/theme_color"

kmp.com.example.bmicalculator.common.ui.model.AppButtonUiState.prototype.toComponent = function () {
    return <div style={{ marginLeft: "16px", marginRight: "16px" }}>
        <Button
            variant="contained"
            onClick={this.onClick}
            disabled={!this.enabled}
            style={{ background: this.enabled ? ThemeColor.ButtonEnableBackground : ThemeColor.ButtonDisableBackground }}
            fullWidth>
            {this.text}
        </Button>
    </div>
}