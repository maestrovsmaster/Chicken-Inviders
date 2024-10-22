package com.example.chickeninviders.game.graphics

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.example.chickeninviders.game.utils.Coord3D

const val dimension = 200f



fun transformPerspective(coord3D: Coord3D): Offset {
    val pair = transformTripleToPair(coord3D.x, coord3D.y, coord3D.z)
    return Offset(pair.first, pair.second)
}


fun transformSize(size: Size, z: Float): Size {
    val pair = transformTripleToPair(size.width, size.height, z)
    return Size(pair.first, pair.second)
}



private fun factor(z: Float):Float{
    var factor = dimension + z
    if (factor == 0f) {
        factor = 0.001f
    }
    return factor
}

fun scale(factor: Float) = dimension / factor

fun transformTripleToPair(x: Float, y: Float, z: Float): Pair<Float, Float>{
    val _scale = scale(factor(z))
    val xProjected = x * _scale //+ GameFrame.centerXpix
    val yProjected = y * _scale //+ GameFrame.centerYpix
    return Pair(xProjected, yProjected)
}