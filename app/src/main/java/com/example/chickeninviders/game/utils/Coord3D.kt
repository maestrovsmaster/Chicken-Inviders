package com.example.chickeninviders.game.utils

data class Coord3D(var x: Float, var y: Float, var z: Float,){

    fun move(speed: Speed3D): Coord3D{
        val nx = x + speed.vx
        val ny = y + speed.vy
        val nz =z + speed.vz
        return this.copy().apply {
            x = nx
            y = ny
            z = nz
        }
    }
}
