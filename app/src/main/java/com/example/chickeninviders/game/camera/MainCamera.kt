package com.example.chickeninviders.game.camera

import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

class MainCamera : Camera(
    Position(
        Coord3D(0f, 0f, 0f),
        Speed3D(0f, 0f, 0f),
        Acceleration3D(0.9f, 0.9f, 0.9f)
    )
) {


    fun move(): MainCamera {
        val newPosition = position.move()
        return MainCamera().apply {
            this.position = newPosition
        }
    }


}