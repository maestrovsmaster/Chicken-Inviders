package com.example.chickeninviders.game.space_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.example.chickeninviders.R

@Composable
fun LifeDisplay(livesLiveData: LiveData<Int>) {
    val lives by livesLiveData.observeAsState(initial = 1)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        for (i in 1..3) {
            Icon(
                imageVector = if (i <= lives) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Heart",
                tint = Color.Red,
                modifier = Modifier.size(24.dp).padding(end = 4.dp)
            )
        }
    }
}