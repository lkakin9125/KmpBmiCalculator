import { Grid2, TextField } from "@mui/material"
import kmp from "BmiCalculator-shared"
import ThemeColor from "../../theme/theme_color"

kmp.com.example.bmicalculator.bmiCalculator.input.model.BmiInputUiState.prototype.toComponent = function () {
    return <Grid2 container={true}>
        <Grid2 size={12}>
            <div
                style={{
                    backgroundColor: ThemeColor.CardBackground,
                    margin: "16px",
                    padding: "8px",
                    borderRadius: 8,
                }}>
                <TextField
                    fullWidth
                    error={this.errorText.length > 0}
                    onChange={(event) => {
                        this.onInputChanged(event.target.value);
                    }}
                    label={this.placeholder}
                    variant="standard"
                    helperText={this.errorText}
                    slotProps={{
                        inputLabel: {
                            style: {
                                color: ThemeColor.ContentPlaceHolder
                            }
                        },
                    }}
                />
            </div>
        </Grid2>
    </Grid2>
}