package com.example.chickeninviders.game.space_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import com.example.chickeninviders.game.viewmodel.MainViewModel


@Composable
fun SpaceCanvas(mainViewModel: MainViewModel) {
    val spaceCrafts = mainViewModel.spaceObjects.observeAsState(emptyList())
    val cameraState by mainViewModel.camera.observeAsState()
    val timerState by mainViewModel.timer.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF000000))
    ) {
        Canvas(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { offset ->

                    },
                    onPress = { offset ->
                        val screenWidth = size.width
                        when {
                            offset.x < screenWidth / 3 -> mainViewModel.clickLeft()
                            offset.x > 2 * screenWidth / 3 -> mainViewModel.clickRight()
                            else -> mainViewModel.clickFire()
                        }
                    },


                    )
            }


        ) {
            timerState?.let {
                spaceCrafts.value.forEach { body ->
                    body.draw(this, cameraState!!.position.coord3D)
                }
            }
        }
    }
}


@Preview
@Composable
fun SpaceAnimationPreview() {
    SpaceCanvas(MainViewModel())
}

