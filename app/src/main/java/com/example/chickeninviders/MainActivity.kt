package com.example.chickeninviders

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.chickeninviders.game.space_screen.GameScreen
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.viewmodel.MainViewModel
import com.example.chickeninviders.ui.theme.ChickenInvidersTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        GameFrame.initialize(this)
        setContent {
            ChickenInvidersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameScreen(mainViewModel);
                }
            }
        }

      //  mainViewModel.camera.observe(this, Observer { camera ->
            // Оновлення UI або виконання дій, якщо значення LiveData змінилося
           // Log.d("LiveDataChange", "New camera position: ${camera.position}")
      //  })
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> {
                // Дії для натискання вгору
                true
            }
            KeyEvent.KEYCODE_DPAD_DOWN -> {
                // Дії для натискання вниз
                true
            }
            KeyEvent.KEYCODE_DPAD_LEFT -> {
                mainViewModel.clickLeft()
                true

            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> {
                mainViewModel.clickRight()
                true
            }
            KeyEvent.KEYCODE_DPAD_CENTER -> {
                mainViewModel.clickFire()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

}

