package com.example.chickeninviders.game.utils

import kotlin.math.abs

data class Speed3D (var vx: Float, var vy: Float, var vz: Float,){

    fun accelerate(acceleration: Acceleration3D): Speed3D{
        vx *= acceleration.ax
        if(abs(vx) <0.01) vx =0f
        vy *= acceleration.ay
        if(abs(vy)<0.01) vy =0f
        vz *= acceleration.az
        if(abs(vz)<0.01) vz =0f
        return this
    }

}