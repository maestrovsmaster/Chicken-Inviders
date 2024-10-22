package com.example.chickeninviders.game.units.cosmic

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.CelestialBody
import com.example.chickeninviders.game.physic.CelestialBodyFactory
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D
import kotlin.random.Random

data class Meteor(
    override var position: Position,
    var color: Color,
) : CelestialBody(position, Size(30f, 1f), color) {

    val radius = Random.nextFloat() * 3;

    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {

        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)


        // if (GameFrame.isInFrame(coord2D, transformedCoords.z))
        // {

        val factRadius = size.width

       // var displayableRadius = factRadius / (transformedCoords.z / 10 + 0.01)
       // if (displayableRadius < 0.1f) displayableRadius = 0.1
       // if (displayableRadius > 10f) displayableRadius = 10.0

        drawScope.drawCircle(
            color,
            radius = radius,//displayableRadius.toFloat(),


            center = androidx.compose.ui.geometry.Offset(coord2D.x, coord2D.y)
        )
        //  }else{
        //     position = position.copy(coord3D = generateRandomCoord() , speed3D = generateRandomSpeed())

        //  }

        if (coord2D.y > GameFrame.heightPx) {
            position =
                position.copy(coord3D = generateRandomCoord(), speed3D = generateRandomSpeed())
        }
    }


    companion object Factory : CelestialBodyFactory {
        override fun create(): CelestialBody {


            val coords = generateInitRandomCoord()
            val speed3D = generateRandomSpeed()
            val acceleration3D = Acceleration3D(1f, 1f, 1f)
            val position = Position(coords, speed3D, acceleration3D)
            // val size = Size(10f, 10f)
            return Meteor(position, generateRandomColor())
        }

        fun generateInitRandomCoord(): Coord3D {
            val x = Random.nextFloat() * GameFrame.widthPx
            val y = Random.nextFloat() * GameFrame.heightPx
            val z = Random.nextFloat() * 2 - 4
            return Coord3D(x, y, z)
        }


        fun generateRandomCoord(): Coord3D {
            val x = Random.nextFloat() * GameFrame.widthPx
            val y = Random.nextFloat() * 150
            val z = Random.nextFloat() * 2 - 4
            return Coord3D(x, y, z)
        }

        fun generateRandomSpeed(): Speed3D {
            val vx = 0.0f//Random.nextFloat() * 2 - 4
            val vy = Random.nextFloat() * 15 + 3
            val vz = 0.0f//Random.nextFloat() * 0//30 - 5
            return Speed3D(vx, vy, vz)
        }
    }


}
