package com.example.chickeninviders.game.space_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.chickeninviders.R

@Composable
fun SafeShieldDisplay(shieldsLiveData: LiveData<Int>, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val shields by shieldsLiveData.observeAsState(initial = 0)

    Box(
        modifier = modifier.height(60.dp).width(60.dp).clickable { onClick() }
    ) {
        if (shields == 0) {
            return@Box
        }

        Image(
            painter = painterResource(id = R.drawable.shield),
            contentDescription = "Bullets",
            modifier = Modifier.size(50.dp).align(Alignment.Center)
        )

        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.Red, shape = CircleShape)
                .align(Alignment.TopEnd)
                .padding(0.dp)
        ) {
            Text(
                text = shields.toString(),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


@Preview
@Composable
fun SafeShieldDisplayPreview(){
    val livedata: MutableLiveData<Int> = MutableLiveData(10)
    SafeShieldDisplay(livedata)
}