package com.example.chickeninviders.game.space_screen.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import kotlinx.coroutines.delay

@Composable
fun TooltipWidget(){

    var showTooltip by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3500)
        showTooltip = false
    }

    if (showTooltip) {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Tooltip(
                message = "Tap left or right to move, in the middle to shoot",
                onClose = { showTooltip = false }
            )
        }
    }

}

@Preview
@Composable
fun TooltipWidgetPreview(){
    TooltipWidget()
}