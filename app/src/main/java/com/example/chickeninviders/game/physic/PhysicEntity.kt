package com.example.chickeninviders.game.physic
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position

abstract  class PhysicEntity(
    open var position: Position,
    open var size: Size,
)
{

    fun transformOffset(cameraOffset: Coord3D): Coord3D {
        val drwX = position.coord3D.x + cameraOffset.x
        val drwY = position.coord3D.y + cameraOffset.y
        val drwZ = position.coord3D.z + cameraOffset.z

        return Coord3D(drwX, drwY, drwZ)
    }


     abstract fun draw(drawScope: DrawScope, cameraOffset: Coord3D)

    open fun update(){
        move()
    }




    internal fun move(){
        position = position.move()
    }

    open var conflictable = false //Ignore for collisions check


    open fun shouldRemove() = false


    open fun shouldRecreate() = false

    open fun name() = "PhysicEntity"

}
