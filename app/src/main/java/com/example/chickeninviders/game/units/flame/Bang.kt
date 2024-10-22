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

class Bang(
    override var position: Position,
    var length: Float = 100f,
    var width: Int = 20,
    var capacity: Int = 20,
    var speed: Speed = Speed.NORMAL,
    var repeat: Boolean = true,
    var colorSet: SparkColorSet

) : PhysicEntity(position, Size(1f, 1f)) {


    lateinit var sparks: MutableList<Spark>

    init {
        sparks = generateSparksCloud(position.coord3D,width,capacity, length, speed,colorSet)
    }


    companion object {





        fun generateSpark(
            coords: Coord3D,
             width: Int, length: Float,speed: Speed, colorSet: SparkColorSet
        ): Spark {

            val scatterX = (Random.nextFloat() * width) - width / 2
            val scatterY = (Random.nextFloat() * width) - width / 2



            val coordWithScatter = coords.copy(x = coords.x + scatterX, y = coords.y + scatterY)



            return Spark(coordWithScatter, MovementVector.ANY, speed, pathLendth = length, radius = 3f,colorSet)


        }

        fun generateSparksCloud(
            coords: Coord3D,
             width: Int, capacity: Int, length: Float,speed: Speed, colorSet: SparkColorSet
        ): MutableList<Spark> {

            val list = mutableListOf<Spark>()

            for (i in 0..capacity) {
                list.add(
                    generateSpark(coords,  width, length, speed = speed, colorSet)
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
                    val spark = generateSpark(position.coord3D,  width, length, speed, colorSet)
                    sparksToAdd.add(spark)
                }
            }
        }

        sparks.removeAll(sparksToRemove)
        sparks.addAll(sparksToAdd)
    }

    override fun shouldRemove():Boolean{
        return sparks.size <= 10
    }



}
