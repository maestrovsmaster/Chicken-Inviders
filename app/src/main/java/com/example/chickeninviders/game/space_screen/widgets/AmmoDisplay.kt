package com.example.chickeninviders.game.space_screen.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.chickeninviders.R

@Composable
fun AmmoDisplay(ammoLiveData: LiveData<Int>) {
    val ammo by ammoLiveData.observeAsState(initial = 0)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Text(
            text = ammo.toString(),
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier.padding(end = 8.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_bullet),
            contentDescription = "Bullets",
            modifier = Modifier.size(24.dp)
        )
    }
}

@Preview
@Composable
fun AmmoDisplayPreview(){
    val livedata: MutableLiveData<Int> = MutableLiveData(10)
    AmmoDisplay(livedata)
}