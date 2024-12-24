import useViewModel from "./HybridViewModel"
import "../navMenu/NavMenuPage"
import "../bmi_calculator/components"
import ThemeColor from "../theme/theme_color"

export default () => {
    let { contentUiState, menuUiState } = useViewModel()
    return <div
        id="content_page_id"
        style={{
            display: "flex",
            height: "100vh",
            width: "100%",
            background: ThemeColor.Background,
        }}>
        <div
            style={{ height: "100%", width: "200px" }}
        >
            {menuUiState?.toComponent()}
        </div>
        <div style={{ height: "100%", width: "2px", background: ThemeColor.Divider }}></div>
        <div style={{ height: "100%", flex: 1 }}>
            {contentUiState?.toComponent()}
        </div>
    </div>
}