package com.example.bmicalculator.android.bmiCalculator.bmiResult

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.common.extension.toColor
import com.example.bmicalculator.android.common.ui.container.BmiCardContainer
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.android.theme.LocalBmiTextStyle
import com.example.bmicalculator.bmiCalculator.bmiResult.model.BmiResultUiState
import com.example.bmicalculator.bmiCalculator.model.BmiCategory

@Composable
fun BmiResult(uiState: BmiResultUiState) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    AnimatedVisibility(visible = visible, enter = fadeIn()) {
        BmiCardContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AnimatedContent(
                    targetState = uiState.bmiResultText,
                    label = ""
                ) { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(
                            4.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        Text(
                            text = "=",
                            style = LocalBmiTextStyle.current.bodyMedium.copy(
                                color = LocalBmiColor.current.contentNormal,
                                fontSize = 10.sp
                            ),
                        )
                        Text(
                            text = text,
                            style = LocalBmiTextStyle.current.headlineMedium,
                            color = LocalBmiColor.current.contentNormal,
                        )
                    }

                }
                AnimatedContent(targetState = uiState.bmiCategoryText) { text ->
                    Text(
                        text = text,
                        style = LocalBmiTextStyle.current.titleSmall,
                        color = uiState.bmiResultTextColorToken.toColor(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewBmiResult() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .background(LocalBmiColor.current.background)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BmiCategory.entries.forEach {
                BmiResult(
                    BmiResultUiState(
                        bmiResultText = "${it.minThreshold * 1.2}",
                        bmiCategoryText = it.displayName,
                        bmiResultTextColorToken = it.color
                    )
                )
            }
        }
    }
}