package com.example.chickeninviders.game.units.bullets

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.graphics.GameFrame.isInFrame2D
import com.example.chickeninviders.game.graphics.drawImageBitmap
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.ArtificialObject
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.physic.speedKoef
import com.example.chickeninviders.game.physic.toInt
import com.example.chickeninviders.game.units.warships.SpaceCraft
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

abstract class Bullet(
    var coords: Coord3D,
    var vector: MovementVector,
    override var size: Size,
    var imageBitmap: ImageBitmap,
    val speed : Float = 15f,
    ) : ArtificialObject(generatePosition( coords, vector, speed), Size(1f, 1f)) {


    override var conflictable = true


    companion object {



        fun generateSpeed( vector: MovementVector, speed: Float = 15f): Speed3D {



            val koefX = vector.speedKoef().sX.toInt()
            val koefY = vector.speedKoef().sY.toInt()

            val vx = koefX * speed
            val vy = koefY * speed

            return Speed3D(vx, vy, 0f)
        }

        fun generatePosition( coords: Coord3D, vector: MovementVector, speed: Float = 15f): Position {
            return Position(
                coord3D = coords,
                speed3D = generateSpeed(vector,speed),
                Acceleration3D(1f, 1f, 1f)
            )
        }
    }


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {
        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)
        drawScope.drawImageBitmap(imageBitmap, coord2D, size)
    }

    override fun update() {
        this.move();
    }


     override fun shouldRemove(): Boolean{
         if(wasBanged) return true
        return !isInFrame2D(position.coord3D.x,position.coord3D.y,size)

    }


    override fun name() = "Bullet"


}
