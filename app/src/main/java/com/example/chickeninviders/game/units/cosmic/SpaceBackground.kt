package com.example.chickeninviders.game.units.cosmic

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.graphics.drawImageBitmap
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

open class SpaceBackground(
    var imageBitmap: ImageBitmap,
) : PhysicEntity(
    Position(
        Coord3D(-100f,-100f,250f),
        Speed3D(0f,0f,0f),
        Acceleration3D(0f,0f,0f)

    ),
    Size(0f,0f)
) {


    var baseImpuls = 12f



    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {
        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)

        drawScope.drawImageBitmap(imageBitmap, coord2D, Size(
GameFrame.widthPx + 200f
            , GameFrame.heightPx + 200f))


    }





}
