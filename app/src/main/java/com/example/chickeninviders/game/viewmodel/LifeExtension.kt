package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.game.physic.PhysicEntity

internal fun MainViewModel.removeLife() {
    _lifes.value = _lifes.value!! - 1
    if(lifes.value == 0){
        stopTimer()
    }
}

internal fun MainViewModel.addLife() {
    var newVal = _lifes.value!! + 1
    if(newVal > 3) newVal = 3
    _lifes.value = newVal

}