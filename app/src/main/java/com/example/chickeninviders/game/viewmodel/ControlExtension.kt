package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.game.physic.MovementImpuls
import com.example.chickeninviders.game.units.warships.PlayerShip




internal fun MainViewModel.shot() {
    getMyStarShip()?.let { myStarship ->
        spaceObjects.value?.add(myStarship.createBullet())
    }
}

internal fun MainViewModel.moveMySheepRight() {
    getMyStarShip()?.let { myStarship ->
        myStarship.moveMySheep(MovementImpuls.RIGHT)
    }
}

internal fun MainViewModel.moveMySheepLeft() {
    getMyStarShip()?.let { myStarship ->
        myStarship.moveMySheep(MovementImpuls.LEFT)
    }
}


internal fun MainViewModel.getMyStarShip(): PlayerShip? {
    spaceObjects.value?.forEach { obj ->
        if (obj is PlayerShip) {

                return obj;

        }
    }
    return null
}