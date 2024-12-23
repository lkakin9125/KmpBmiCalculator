import { Button } from "@mui/material"
import kmp from "BmiCalculator-shared"

kmp.com.example.bmicalculator.common.ui.model.AppFlatButtonUiState.prototype.toComponent = function () {
    return <div style={{ marginLeft: "16px", marginRight: "16px" }}>
        <Button
            variant="text"
            onClick={this.onClick}
            disabled={!this.enabled}
            fullWidth>
            {this.text}
        </Button>
    </div>
}
