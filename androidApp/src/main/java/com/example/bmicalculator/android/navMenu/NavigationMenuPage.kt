package com.example.bmicalculator.android.navMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.common.ui.button.AppFlatButton
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.common.ui.model.AppFlatButtonUiState
import com.example.bmicalculator.navMenu.model.NavigationMenuOption
import com.example.bmicalculator.navMenu.model.NavigationMenuPageUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavigationMenuPage(
    navigation: (String) -> Unit
) {
    val viewModel = koinViewModel<NavigationMenuViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.onClickEvent
            .onEach { navigation.invoke(it.navPath) }
            .launchIn(this)
    }
    NavigationMenuPage(uiState)
}

@Composable
private fun NavigationMenuPage(
    uiState: NavigationMenuPageUiState
) {
    Column(
        modifier = Modifier
            .background(LocalBmiColor.current.background)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        uiState.buttons.forEach {
            AppFlatButton(it)
        }
    }

}

@Preview
@Composable
private fun NavigationMenuPage() {
    MyApplicationTheme {
        NavigationMenuPage(
            uiState = NavigationMenuPageUiState(
                NavigationMenuOption.entries.map {
                    AppFlatButtonUiState(text = it.displayText, enabled = true) {}
                }
            )
        )
    }
}