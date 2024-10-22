package com.example.chickeninviders.game.units.bullets

import androidx.compose.ui.geometry.Size
import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.utils.Coord3D

class ChickenBullet(coords: Coord3D) : Bullet(
    coords,
    MovementVector.BACK, Size(50f, 50f), SpawnScript.eggBitmap, speed = 7f
) {

    override fun name() = "ChickenBullet"
}