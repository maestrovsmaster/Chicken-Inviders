package com.example.chickeninviders.game.units.bullets

import androidx.compose.ui.geometry.Size
import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.utils.Coord3D

class PlayersBullet(coords: Coord3D) : Bullet(
    coords,
    MovementVector.FORWARD, Size(50f,50f), SpawnScript.bulletBitmap, speed = 16f
) {

    override fun name() = "PlayersBullet"
}