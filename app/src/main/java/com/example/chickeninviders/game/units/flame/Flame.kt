package com.example.chickeninviders.game.units.flame

import android.health.connect.datatypes.units.Length
import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.gameplay.calculateDistance
import com.example.chickeninviders.game.graphics.GameFrame.standartZ
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.physic.speedKoef
import com.example.chickeninviders.game.physic.toInt
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D
import kotlin.random.Random

class Flame(
    override var position: Position,
    var vector: MovementVector,
    var length: Int = 5,
    var width: Int = 20,
    var capacity: Int = 20,
    var speed: Speed = Speed.NORMAL,
    var repeat: Boolean = true

) : PhysicEntity(position, Size(1f, 1f)) {


    lateinit var sparks: MutableList<Spark>

    init {
        sparks = generateSparksCloud(position.coord3D,vector,width,capacity, length, speed)
    }


    companion object {





        fun generateSpark(
            coords: Coord3D,
            vector: MovementVector, width: Int, length: Int,speed: Speed
        ): Spark {

            val scatterX = (Random.nextFloat() * width) - width / 2
            val scatterY = (Random.nextFloat() * width) - width / 2

            val lenOffset = when(vector){
                MovementVector.ANY -> {0f}
                MovementVector.LEFT,MovementVector.RIGHT -> {
                    width - scatterX
                }

                MovementVector.FORWARD,MovementVector.BACK -> {
                    width - scatterY
                }

            }

            val coordWithScatter = coords.copy(x = coords.x + scatterX, y = coords.y + scatterY)

            return Spark(coordWithScatter, vector, speed, pathLendth = lenOffset*5)


        }

        fun generateSparksCloud(
            coords: Coord3D,
            vector: MovementVector, width: Int, capacity: Int, length: Int,speed: Speed
        ): MutableList<Spark> {

            val list = mutableListOf<Spark>()

            for (i in 0..capacity) {
                list.add(
                    generateSpark(coords, vector, width, length, speed = speed)
                )
            }
            return list
        }


    }


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {

        sparks.map {
            it.draw(drawScope, cameraOffset)
        }


    }

    override fun update() {
        this.move()

        val sparksToRemove = mutableListOf<Spark>()
        val sparksToAdd = mutableListOf<Spark>()

        sparks.forEach {
            it.move()
            if (it.shouldRemove()) {
                sparksToRemove.add(it)
                if(repeat) {
                    val spark = generateSpark(position.coord3D, vector, width, length, speed)
                    sparksToAdd.add(spark)
                }
            }
        }

        sparks.removeAll(sparksToRemove)
        sparks.addAll(sparksToAdd)
    }

    override fun shouldRemove():Boolean{
        return sparks.size == 0
    }

    override fun name() = "Flame"

}
