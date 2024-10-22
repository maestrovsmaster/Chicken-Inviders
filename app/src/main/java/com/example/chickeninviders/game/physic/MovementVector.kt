package com.example.chickeninviders.game.physic

import kotlin.random.Random

enum class MovementVector {
    ANY, LEFT, RIGHT, FORWARD, BACK



}

enum class MoveType{
    Increment, Decrement, Zero;


}

fun MoveType.toInt(): Int{
    return when(this){
        MoveType.Increment -> 1
        MoveType.Decrement -> -1
        MoveType.Zero -> 0
    }
}

fun Int.moveTypeFromInt():MoveType{
    if(this > 0){
        return MoveType.Increment
    }else if(this < 0){
        return MoveType.Decrement
    }else return MoveType.Zero
}




data class SpeedVector(val sX: MoveType, var sY: MoveType)

fun MovementVector.speedKoef(): SpeedVector{
    return when(this){
        MovementVector.ANY -> {
            val sx = Random.nextInt()*2 - 1
            val sy = Random.nextInt()*2 - 1
            SpeedVector(sx.moveTypeFromInt(), sy.moveTypeFromInt())
        }
        MovementVector.LEFT -> SpeedVector(MoveType.Decrement, MoveType.Zero)
        MovementVector.RIGHT -> SpeedVector(MoveType.Increment, MoveType.Zero)
        MovementVector.FORWARD -> SpeedVector(MoveType.Zero, MoveType.Decrement)
        MovementVector.BACK -> SpeedVector(MoveType.Zero, MoveType.Increment)
    }
}


