package com.example.chickeninviders.game.camera

import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

abstract class Camera(var position: Position){


    val speedFactor = 1.0f

    fun turnLeft(){
        val speed = Speed3D(-1f * speedFactor,0f,0.0f)
        position.speed3D = speed
    }


    fun turnRight(){
        val speed = Speed3D(1f * speedFactor,0f,0.0f)
        position.speed3D = speed
    }



}