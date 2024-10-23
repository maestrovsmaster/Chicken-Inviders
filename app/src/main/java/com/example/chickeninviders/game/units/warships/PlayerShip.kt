package com.example.chickeninviders.game.units.warships

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.graphics.GameFrame.nearBorder
import com.example.chickeninviders.game.graphics.ScreenBorder
import com.example.chickeninviders.game.physic.MovementImpuls
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.units.artifacts.SafeSphere
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

    //val sphere = SpawnScript.initTestSphere()
   // mainViewModel.addSpaceObject(sphere)
    var guardSphere: SafeSphere? = null



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

    /**
     *return true if guard sphere was enabled
     */
    fun enableGuardSphere(): Boolean {
        if(guardSphere == null) {
            Log.d("PlayerShip", "enableGuardSphere")
            guardSphere = SafeSphere(this.position.copy(coord3D = this.position.coord3D.copy(x = this.position.coord3D.x + size.width/2, y = this.position.coord3D.y + size.height/2))){
                Log.d("PlayerShip", "disableGuardSphere")
                disableGuardSphere()
            }
            return true
        }
        return false
    }

    fun disableGuardSphere() {
        guardSphere = null
    }

    fun hasGuardSphere(): Boolean {
        return guardSphere != null
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

        guardSphere?.position?.speed3D = newSpeed
        guardSphere?.position?.coord3D?.x = position.coord3D.x+size.width/2f - 5
    }

    override fun update() {

        val nearBorder = nearBorder(position.coord3D.x, size)

        if(nearBorder == ScreenBorder.LEFT){
            position =     position.copy(speed3D = position.speed3D.copy(vx = 0.9f))
        }
        if(nearBorder == ScreenBorder.RIGHT){
            position =     position.copy(speed3D = position.speed3D.copy(vx = -0.9f))
        }
        this.move()
        flame.update()

        guardSphere?.update()


    }

    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {
        super.draw(drawScope, cameraOffset)
        guardSphere?.draw(drawScope, cameraOffset)
    }


}