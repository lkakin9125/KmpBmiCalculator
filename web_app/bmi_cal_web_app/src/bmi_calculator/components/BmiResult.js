import kmp from "BmiCalculator-shared"
import ThemeColor from "../../theme/theme_color"

kmp.com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState.prototype.toComponent = function () {
    return <div
        style={{
            backgroundColor: ThemeColor.CardBackground,
            margin: "16px",
            padding: "8px",
            borderRadius: 8,
        }}>
        <div style={{
            display: "flex",
            color:ThemeColor.ContentNormal
        }}>
            <div style={{ fontSize: "10px",marginLeft:"auto",marginRight:"8px" }}>=</div>
            <div style={{ fontSize: "20px", lineHeight: "14px", fontWeight: 700,marginRight:"auto" }}>
                {this.bmiResultText}
            </div>
        </div>
        <div style={{
            fontSize:"14px",
            fontWeight:600,
            lineHeight:"20px",
            marginTop:"8px",
            color: this.bmiResultTextColorToken.toCssColor()
        }}>
            <center>
            {this.bmiCategoryText}
            </center>
        </div>
    </div>
}