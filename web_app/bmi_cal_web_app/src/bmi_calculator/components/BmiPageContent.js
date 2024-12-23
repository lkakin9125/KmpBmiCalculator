import kmp from "BmiCalculator-shared"
import ThemeColor from "../../theme/theme_color"
import { Container } from "@mui/material"
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';


kmp.com.example.bmicalculator.bmiCalculator.model.BmiPageContentUiState.prototype.toComponent = function () {
    return <div
        id="content_page_id"
        style={{
            height: "100vh",
            width: "100%",
            background: ThemeColor.Background,
        }}>
        <Container>
            <div style={{
                height: "100vh",
                display: "flex",
                flexDirection: "column"
            }}>
                <Toolbar disableGutters>
                    <Typography
                        variant="h6"
                        noWrap
                        sx={{
                            mr: 2,
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 700,
                            letterSpacing: '.3rem',
                            color: ThemeColor.ContentNormal,
                            textDecoration: 'none',
                        }}
                    >
                        {this.title}
                    </Typography>
                </Toolbar>
                <div style={{ flex: 1, overflowY: "auto" }}>
                    {this?.heightInputUiState?.toComponent()}
                    {this?.weightInputUiState?.toComponent()}
                    {this?.resultUiState?.toComponent()}
                </div>
                <div style={{ marginBottom: 8 }}>
                    {this?.buttonUiState?.toComponent()}
                </div>
            </div>
        </Container>
    </div>

}