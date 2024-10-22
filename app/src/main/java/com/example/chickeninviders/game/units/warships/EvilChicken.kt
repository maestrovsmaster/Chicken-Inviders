package com.example.chickeninviders.game.units.warships

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.graphics.GameFrame.heightPx
import com.example.chickeninviders.game.graphics.GameFrame.widthPx
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.units.bullets.Bullet
import com.example.chickeninviders.game.units.bullets.ChickenBullet
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D
import kotlin.math.abs
import kotlin.random.Random

class EvilChicken(
    name: String,
    imageBitmap: ImageBitmap,
) : SpaceCraft(name, initRandomPosition(), Size(100f,100f), imageBitmap, 1000000) {




    private var randomXtarget = 0.0f;
    private var vxKoef = 1.0f;
    private var xSpeed = 4.2f

    init {
        setRandomXmovement()
    }

    companion object{
        fun initRandomPosition(): Position{
            val coord3D = initRandomCoordinats(-50f)
            val speed3D = Speed3D(0f,1.4f,0f)
            val ax = Acceleration3D(1.0f,1.0f,0f)
            return Position(coord3D,speed3D,ax)
        }
    }

    fun setRandomXmovement(){
        randomXtarget = (Random.nextFloat() * GameFrame.widthPx-40)+20

        vxKoef = (  randomXtarget - position.coord3D.x) / abs( randomXtarget-position.coord3D.x )

        val vx = xSpeed * vxKoef;
        position = position.copy(speed3D = position.speed3D.copy(vx = vx))
    }



    fun checkXrandomAchievement(){
        if(vxKoef >=1){
            if(position.coord3D.x >= randomXtarget){
                setRandomXmovement()
            }
        }else{
            if(position.coord3D.x <= randomXtarget){
                setRandomXmovement()
            }
        }
    }


    override fun update(){
        this.move();
        checkXrandomAchievement()
        checkOutOfFrame()
    }

    override fun createBullet(): Bullet {
        val flameX = (position.coord3D.x + size.width/2) - 25
        val flameY = position.coord3D.y + size.height + 10
        val coord3D = position.coord3D.copy(x = flameX, y = flameY)
        return ChickenBullet(coord3D)
    }

    fun checkRandomShot(): Bullet? {
        val shotChanse = Random.nextInt(250)
        if(shotChanse == 10){
            return createBullet()
        }
        return null
    }



    private var outOfFrame = false

    private fun checkOutOfFrame(){
        val coord2D = transformPerspective(position.coord3D)
        outOfFrame =  !( coord2D.x > 0 && coord2D.x < widthPx + 100 && coord2D.y > -100 && coord2D.y < heightPx)
    }


    override fun shouldRemove(): Boolean {
        if(wasBanged) return true
        return outOfFrame
    }


    override fun shouldRecreate(): Boolean {
        if(wasBanged) return true
        return outOfFrame
    }



}