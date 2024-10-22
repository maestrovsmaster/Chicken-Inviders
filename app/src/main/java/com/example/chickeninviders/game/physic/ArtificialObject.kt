package com.example.chickeninviders.game.physic

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.graphics.GameFrame.standartZ
import com.example.chickeninviders.game.units.flame.Bang
import com.example.chickeninviders.game.units.flame.BangType
import com.example.chickeninviders.game.units.flame.SparkColorSet
import com.example.chickeninviders.game.units.flame.Speed
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D
import kotlin.random.Random

abstract class ArtificialObject(
    override var position: Position,
    override var size: Size,
) : PhysicEntity(
    position, size
) {

    var wasBanged = false


    override var conflictable = true

    companion object {

        fun initRandomCoordinats(baseY: Float): Coord3D{
            val x =  Random.nextFloat() * (GameFrame.widthPx- 300) +150
            val y =  baseY
            val z = standartZ
            return Coord3D(x, y, z)
        }



    }


    fun initBangFlame(bangType: BangType): Bang {

        val speed3D = Speed3D(0f, 0f, 0f)
        val ax = Acceleration3D(1.0f, 1.0f, 0f)
        val coords = Coord3D(
            position.coord3D.x + size.width / 2f,
            position.coord3D.y + size.height / 2f,
            position.coord3D.z
        )
        val pos1 = Position(coords, speed3D, ax)


        val sparkColorSet = when(bangType){
            BangType.Bang -> SparkColorSet(Color.White, Color.Yellow, Color.Red)
            BangType.Salut -> SparkColorSet(Color.Green, Color.Cyan, Color.Blue)
            BangType.Target -> SparkColorSet(Color.White, Color.Red, Color.Gray)
            BangType.Accident -> SparkColorSet(Color.Gray, Color.Yellow, Color.Gray)
        }

        val length = when(bangType){
            BangType.Bang -> 220f
            BangType.Salut -> 90f
            BangType.Target -> 180f
            BangType.Accident -> 90f
        }

        val capacity = when(bangType){
            BangType.Bang -> 70
            BangType.Salut -> 40
            BangType.Target -> 50
            BangType.Accident -> 30
        }

        return Bang(pos1, speed = Speed.NORMAL, capacity = capacity, length = length, repeat = false, colorSet = sparkColorSet)
    }





    open fun bang(bangType: BangType): Bang {

        wasBanged = true
        return initBangFlame(bangType)
    }

    override fun name() = "ArtificialObject"


}