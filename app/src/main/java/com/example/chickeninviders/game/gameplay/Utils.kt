package com.example.chickeninviders.game.gameplay

import kotlin.math.pow
import kotlin.math.sqrt

fun calculateDistance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val dx = x2 - x1
    val dy = y2 - y1
    return sqrt(dx.pow(2) + dy.pow(2))
}