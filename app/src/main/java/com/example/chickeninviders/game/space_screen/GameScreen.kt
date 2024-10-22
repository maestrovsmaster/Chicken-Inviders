package com.example.chickeninviders.game.space_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chickeninviders.game.space_screen.widgets.AmmoDisplay
import com.example.chickeninviders.game.space_screen.widgets.GameOverDialog
import com.example.chickeninviders.game.space_screen.widgets.LifeDisplay
import com.example.chickeninviders.game.space_screen.widgets.Tooltip
import com.example.chickeninviders.game.space_screen.widgets.TooltipWidget
import com.example.chickeninviders.game.viewmodel.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun GameScreen(mainViewModel: MainViewModel) {






        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF000066))
        ) {


            GameWidget(mainViewModel)

            AmmoDisplay(mainViewModel.ammo)

            LifeDisplay(livesLiveData = mainViewModel.lifes)

            TooltipWidget()

            GameOverDialog(mainViewModel.lifes,{
                System.exit(0)
            })

        }

}






@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(MainViewModel())
}