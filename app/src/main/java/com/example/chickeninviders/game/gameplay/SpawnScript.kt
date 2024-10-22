package com.example.chickeninviders.game.gameplay

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.example.chickeninviders.R
import com.example.chickeninviders.game.graphics.GameFrame
import com.example.chickeninviders.game.graphics.GameFrame.standartZ
import com.example.chickeninviders.game.physic.MovementVector
import com.example.chickeninviders.game.units.warships.PlayerShip
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.artifacts.SafeSphere
import com.example.chickeninviders.game.units.warships.EvilChicken
import com.example.chickeninviders.game.units.flame.Flame
import com.example.chickeninviders.game.utils.Acceleration3D
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.game.utils.Speed3D

object SpawnScript {

    lateinit var  chickenBitmap : ImageBitmap
    lateinit var userShipBitmap : ImageBitmap

    lateinit var bulletBitmap : ImageBitmap
    lateinit var eggBitmap : ImageBitmap

    lateinit var ammoParcelBitmap : ImageBitmap
    lateinit var medicineParcelBitmap : ImageBitmap

    @Composable
    fun init(){
        chickenBitmap = ImageBitmap.imageResource(id = R.drawable.ic_big_chicken)
        userShipBitmap = ImageBitmap.imageResource(id = R.drawable.ic_warship2)
        bulletBitmap = ImageBitmap.imageResource(id = R.drawable.ic_bullet)
        eggBitmap = ImageBitmap.imageResource(id = R.drawable.ic_egg)
        ammoParcelBitmap = ImageBitmap.imageResource(id = R.drawable.ic_box_ammo_10)
        medicineParcelBitmap = ImageBitmap.imageResource(id = R.drawable.ic_box_medicine_10)
    }


    fun initMySpaceCraft(): PlayerShip {
        val coord3D = Coord3D(GameFrame.widthPx / 2f, GameFrame.heightPx * 0.8f, standartZ)
        val speed3D = Speed3D(0f,0f,0f)
        val ax = Acceleration3D(0.95f,1.0f,0f)
        val position = Position(coord3D,speed3D,ax)

        val size = Size(160f,160f)

        val ammo = 100

        return PlayerShip("MySpaceCraft",position, size, userShipBitmap, ammo,)
    }





    fun createChickenCraft(): PhysicEntity {

        return EvilChicken("MySpaceCraft", chickenBitmap )
    }


    fun initTestFlame(): Flame {
        val coord3D = Coord3D(GameFrame.widthPx / 2f, GameFrame.heightPx * 0.5f, standartZ)
        val speed3D = Speed3D(0f,0f,0f)
        val ax = Acceleration3D(0.95f,1.0f,0f)
        val position = Position(coord3D,speed3D,ax)



        return Flame(position, MovementVector.BACK)
    }

    fun initTestSphere(): SafeSphere {
        val coord3D = Coord3D(GameFrame.widthPx / 2f, GameFrame.heightPx / 2f, standartZ)
        val speed3D = Speed3D(0f,0f,0f)
        val ax = Acceleration3D(1.05f,1.0f,1.0f)
        val position = Position(coord3D,speed3D,ax)



        return SafeSphere(position)
    }


}