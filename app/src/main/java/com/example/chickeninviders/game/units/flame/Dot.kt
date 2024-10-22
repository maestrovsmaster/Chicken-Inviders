package com.example.chickeninviders.game.units.flame

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.gameplay.calculateDistance
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

class Dot(
    var coords: Coord3D,
    var radius: Float = 1f,
    var colorSet: SparkColorSet = SparkColorSet(Color.White, Color.Yellow, Color.Red)

) : PhysicEntity(generatePosition(coords), Size(1f, 1f)) {

    private val startPosition: Position = position



    companion object {




        fun generatePosition( coords: Coord3D): Position {
            return Position(
                coord3D = coords,
                speed3D = Speed3D(0f, 0f, 0f),
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

        return false

    }

    fun color():Color{
        val currentDistance = calculateDistance(position.coord3D.x, position.coord3D.y, startPosition.coord3D.x, startPosition.coord3D.y)


            return colorSet.end

    }


}
