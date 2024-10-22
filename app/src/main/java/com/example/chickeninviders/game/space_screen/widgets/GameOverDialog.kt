package com.example.chickeninviders.game.space_screen.widgets
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameOverDialog(livesLiveData: LiveData<Int>, onDismiss: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val lives by livesLiveData.observeAsState(initial = 1)

    if (lives == 0) {

        AlertDialog(
            onDismissRequest = { /* Prevent dismiss on outside click */ },
            title = {
                Text(text = "Game Over")
            },
            text = {
                Text("You have no more lives left.")
            },
            confirmButton = {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            delay(1000) // Delay for 1 second before exiting
                            onDismiss()
                        }
                    }
                ) {
                    Text("Exit Game")
                }
            }
        )
    }
}