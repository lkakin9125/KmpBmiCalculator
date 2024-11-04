package com.example.bmicalculator.android.common.ui.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmicalculator.android.MyApplicationTheme
import com.example.bmicalculator.android.theme.LocalBmiColor
import com.example.bmicalculator.android.theme.LocalBmiTextStyle

@Composable
fun BmiCardContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = LocalBmiColor.current.cardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        content = content
    )
}

@Preview
@Composable
private fun PreviewBmiCardContainer() {
    MyApplicationTheme {
        Column(
            modifier = Modifier
                .background(LocalBmiColor.current.background)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BmiCardContainer(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "hello full row",
                    modifier = Modifier.padding(16.dp),
                    style = LocalBmiTextStyle.current.bodyMedium,
                    color = LocalBmiColor.current.contentNormal
                )
            }
            BmiCardContainer(
                modifier = Modifier,
            ) {
                Text(
                    "hello wrap content row",
                    modifier = Modifier.padding(16.dp),
                    style = LocalBmiTextStyle.current.bodyMedium,
                    color = LocalBmiColor.current.contentNormal
                )
            }
        }
    }
}