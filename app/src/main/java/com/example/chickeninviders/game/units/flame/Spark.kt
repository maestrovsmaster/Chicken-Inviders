package com.example.chickeninviders.game.units.flame

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.gameplay.calculateDistance
import com.example.chickeninviders.game.graphics.GameFrame.standartZ
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.physic.speedKoef
import com.example.chickeninviders.game.physic.toInt
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D
import kotlin.random.Random

class Spark(
    var coords: Coord3D,
    var vector: MovementVector,
    var speed: Speed,
    var pathLendth: Float,
    var radius: Float = 1f,
    var colorSet: SparkColorSet = SparkColorSet(Color.White, Color.Yellow, Color.Red)

) : PhysicEntity(generatePosition(speed, coords, vector), Size(1f, 1f)) {

    private val startPosition: Position = position



    companion object {



        fun generateSpeed(speed: Speed, vector: MovementVector): Speed3D {


            val speedMultiplier = when(speed){
                Speed.SLOW -> 1.05f
                Speed.NORMAL -> 1.2f
                Speed.HIGHT -> 1.6f
            }

             val speedOffset =   when(speed){
                Speed.SLOW -> 1f
                Speed.NORMAL -> 5f
                Speed.HIGHT -> 12f
            }

             val speedDiapazon =  when(speed){
                Speed.SLOW -> 3f
                Speed.NORMAL -> 7f
                Speed.HIGHT -> 12f
            }

            val randomXoffset = when(vector){
                MovementVector.LEFT ,MovementVector.RIGHT -> { speedOffset }
                else -> { 0f }
            }

            val randomYoffset = when(vector){
                MovementVector.BACK ,MovementVector.FORWARD -> { speedOffset }
                else -> { 0f }
            }


            val koefX = vector.speedKoef().sX.toInt().toFloat()
            val koefY = vector.speedKoef().sY.toInt().toFloat()



            var baseSpeedX = Random.nextFloat() * speedDiapazon + randomXoffset
            if(baseSpeedX != 0f){
                baseSpeedX *= speedMultiplier
            }

            var baseSpeedY = Random.nextFloat() * speedDiapazon + randomYoffset
            if(baseSpeedY != 0f){
                baseSpeedY *= speedMultiplier
            }


            if(baseSpeedX == 0f && baseSpeedY == 0f){
                val correctorXorYFactor = Random.nextInt()*1
                if(correctorXorYFactor == 0){
                    baseSpeedX = speedOffset
                }else{
                    baseSpeedY = speedOffset
                }
            }



            return Speed3D(baseSpeedX * koefX, baseSpeedY * koefY, 0f)
        }

        fun generatePosition(speed: Speed, coords: Coord3D, vector: MovementVector): Position {
            return Position(
                coord3D = coords,
                speed3D = generateSpeed(speed, vector),
                Acceleration3D(1f, 1f, 1f)
            )
        }
    }


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {
        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)
        val color = color()
        drawScope.drawCircle(
            color,
            radius = radius,//displayableRadius.toFloat(),


            center = androidx.compose.ui.geometry.Offset(coord2D.x, coord2D.y)
        )


    }

    override fun update() {

        this.move();


    }


     override fun shouldRemove(): Boolean{

        val currentDistance = calculateDistance(position.coord3D.x, position.coord3D.y, startPosition.coord3D.x, startPosition.coord3D.y)


        return currentDistance >= pathLendth

    }

    fun color():Color{
        val currentDistance = calculateDistance(position.coord3D.x, position.coord3D.y, startPosition.coord3D.x, startPosition.coord3D.y)

        if(currentDistance / pathLendth  <= 0.3){
            return colorSet.start
        }else

        if(currentDistance / pathLendth  <= 0.6){
            return colorSet.middle
        }else{
            return colorSet.end
        }
    }


}
