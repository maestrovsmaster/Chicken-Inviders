package com.example.chickeninviders.game.units.cosmic

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.CelestialBody
import com.example.chickeninviders.game.physic.CelestialBodyFactory
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

data class Star(
    override var position: Position,
    override var size: Size,
     var color: Color,
) : CelestialBody(position, size, color) {

    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {

        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D =  transformPerspective(transformedCoords)

        if(coord2D.x > 0 && coord2D.x < 1300 && coord2D.y > 0 && coord2D.y <2000  && transformedCoords.z >10 && transformedCoords.z <10000 ) {

            val factRadius = size.width

            var displayableRadius = factRadius / (transformedCoords.z / 10 + 0.01)

            if(displayableRadius < 0.2) displayableRadius = 0.5
            if(displayableRadius > 2.5) displayableRadius = 2.5


            val rr = displayableRadius.toFloat()
            val r2 = rr /2
            drawScope.drawCircle( color,
                radius = rr, center = androidx.compose.ui.geometry.Offset(coord2D.x, coord2D.y)
            )
            drawScope.drawCircle( color, radius = r2, center = androidx.compose.ui.geometry.Offset(coord2D.x-rr, coord2D.y))
            drawScope.drawCircle( color, radius = r2, center = androidx.compose.ui.geometry.Offset(coord2D.x+rr, coord2D.y))
            drawScope.drawCircle( color, radius = r2, center = androidx.compose.ui.geometry.Offset(coord2D.x, coord2D.y-rr))
            drawScope.drawCircle( color, radius = r2, center = androidx.compose.ui.geometry.Offset(coord2D.x, coord2D.y+rr))
        }
    }


    companion object Factory : CelestialBodyFactory {
        override fun create(): CelestialBody {
            val coords = generateRandomCoord(3000.0f, 5000f, 30f)
            val speed3D = Speed3D(0f, 0f, 0f)
            val acceleration3D = Acceleration3D(0f, 0f, 0f)
            val position = Position(coords, speed3D, acceleration3D)
            val size = Size(10f,10f)
            return Star(position, size, generateRandomColor())
        }
    }


}
