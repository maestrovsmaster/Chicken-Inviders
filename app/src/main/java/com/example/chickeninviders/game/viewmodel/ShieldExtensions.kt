package com.example.chickeninviders.game.viewmodel

import android.os.Handler
import android.os.Looper
import com.example.chickeninviders.MAX_SHIELDS
import com.example.chickeninviders.SHIELD_ACTIVE_TIME

public fun MainViewModel.activateShield() {
    val newShields = _shields.value!! - 1
    if(newShields >= 0) {

        val result = enableGuardSphereForMySheep()
        if(result){
            _shields.postValue(newShields)
        }
    }
}

public fun MainViewModel.addShield() {
    var newValue = _shields.value!! + 1
    if (newValue > MAX_SHIELDS) {
        newValue = MAX_SHIELDS
    }
    _shields.postValue(newValue)
}