package com.example.chickeninviders.game.utils

data class Position(var coord3D: Coord3D, var speed3D: Speed3D, var acceleration3D: Acceleration3D){

    fun move(): Position{
        speed3D.accelerate(acceleration3D)

        val coord = coord3D.move(speed3D)
        return this.copy().apply {
            coord3D = coord
        }
    }





}
