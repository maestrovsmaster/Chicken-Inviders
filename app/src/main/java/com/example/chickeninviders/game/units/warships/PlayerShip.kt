package com.example.chickeninviders.game.units.warships

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import com.example.chickeninviders.game.graphics.GameFrame.isInFrameWidth
import com.example.chickeninviders.game.physic.MovementImpuls
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.units.bullets.Bullet
import com.example.chickeninviders.game.units.bullets.PlayersBullet
import com.example.chickeninviders.game.units.flame.Flame
import com.example.chickeninviders.game.units.flame.Speed
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

class PlayerShip(
    name: String,
    position: Position,
    size: Size,
    imageBitmap: ImageBitmap,
    ammo: Int,
) : SpaceCraft(name, position, size, imageBitmap, ammo) {

    var baseImpuls = 20f

    lateinit var flame: Flame



    init {
        val flameX = (position.coord3D.x + size.width/2) - 5
        val flameY = position.coord3D.y + size.height + 28
        flame = initFlame(coord3D = position.coord3D.copy(x = flameX, y = flameY))
    }

    companion object {

        fun initFlame(coord3D: Coord3D): Flame {

            val speed3D = Speed3D(0f, 0f, 0f)
            val ax = Acceleration3D(0.95f, 1.0f, 0f)
            val position = Position(coord3D, speed3D, ax)



            return Flame(position, MovementVector.BACK, speed = Speed.HIGHT)
        }
    }



    override fun createBullet(): Bullet {
        val flameX = (position.coord3D.x + size.width/2) - 25
        val flameY = position.coord3D.y - 20
        val coord3D = position.coord3D.copy(x = flameX, y = flameY)
        return PlayersBullet(coord3D)
    }


    fun moveMySheep(movementImpuls: MovementImpuls) {


        val currentSpeed = position.speed3D

        val newSpeed = when (movementImpuls) {
            MovementImpuls.LEFT -> currentSpeed.copy(vx = -1f * baseImpuls)
            MovementImpuls.RIGHT -> currentSpeed.copy(vx = 1f * baseImpuls)
            MovementImpuls.FORWARD -> currentSpeed.copy(vy = -1f * baseImpuls)
            MovementImpuls.BACK -> currentSpeed.copy(vy = 1f * baseImpuls)

        }
        position.speed3D = newSpeed

        flame.position.speed3D = newSpeed

        flame.position.coord3D.x = position.coord3D.x+size.width/2f - 5
    }

    override fun update() {

        val isInFrame = isInFrameWidth(position.coord3D.x, size)
        if (!isInFrame) {
            position =
                position.copy(speed3D = position.speed3D.copy(vx = -1f * position.speed3D.vx))
        }
        this.move();
        flame.update()

    }


}