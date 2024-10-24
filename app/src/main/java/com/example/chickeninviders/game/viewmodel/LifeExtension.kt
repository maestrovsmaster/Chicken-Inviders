package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.MAX_LIFES

internal fun MainViewModel.removeLife() {
    _lifes.value = _lifes.value!! - 1
    if(lifes.value == 0){
        stopTimer()
    }
}

internal fun MainViewModel.addLife() {
    var newVal = _lifes.value!! + 1
    if(newVal > MAX_LIFES) newVal = MAX_LIFES
    _lifes.value = newVal

}

