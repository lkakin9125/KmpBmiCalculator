import useViewModel from "./NavMenuViewModel"
import "./NavMenuPage"
import "../common/"

export default () => {
    let { uiState } = useViewModel()
    return uiState?.toComponent()
}