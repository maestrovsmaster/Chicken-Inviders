package com.example.chickeninviders.game.units.warships

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.graphics.drawImageBitmap
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.ArtificialObject
import com.example.chickeninviders.game.units.bullets.Bullet
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position

abstract class SpaceCraft(
    var name: String,
    override var position: Position,
    override var size: Size,
    var imageBitmap: ImageBitmap,
    var ammo: Int,
) : ArtificialObject(position, size) {


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {
        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)

        drawScope.drawImageBitmap(imageBitmap, coord2D, size)


    }

    abstract fun createBullet(): Bullet


}
