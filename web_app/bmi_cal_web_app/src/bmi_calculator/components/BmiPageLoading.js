import kmp from "BmiCalculator-shared"
import ThemeColor from "../../theme/theme_color"
import { CircularProgress, Container } from "@mui/material"

let loadingState = kmp.com.example.bmicalculator.bmiCalculator.model.BmiPageLoadingUiState
loadingState.toComponent = () => {
   return <div
      id="content_page_id"
      style={{
         display:"flex",
         height: "100vh",
         width: "100%",
         background: ThemeColor.Background,
      }}>
      <div style={{margin:"auto"}}>
         <CircularProgress />
      </div>
   </div>
}