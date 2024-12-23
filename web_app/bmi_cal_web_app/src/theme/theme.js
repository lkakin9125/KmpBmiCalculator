import ThemeColor from "./theme_color";
import { createTheme } from "@mui/material";


const theme = createTheme({
    components: {
        MuiTextField: {
            styleOverrides: {
                root: {
                    '& .MuiInput-underline:before': {
                        borderBottomColor: ThemeColor.Transparent, // Default underline color 
                    }, '& .MuiInput-underline:hover:not(.Mui-disabled):before': {
                        borderBottomColor: ThemeColor.Transparent, // Hover underline color 
                    }, '& .MuiInput-underline:after': {
                        borderBottomColor: ThemeColor.Transparent, // Focused underline color 
                    },
                    '& .MuiInputBase-input': {
                        color: ThemeColor.ContentNormal, // Text color
                    },
                },
            },
        },
    },
});

export default theme