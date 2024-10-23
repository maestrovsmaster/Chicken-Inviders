package com.example.chickeninviders.game.space_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.chickeninviders.game.viewmodel.MainViewModel
import com.example.chickeninviders.R
import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.physic.CelestialBody
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.artifacts.SafeSphere
import com.example.chickeninviders.game.units.cosmic.Meteor
import com.example.chickeninviders.game.units.cosmic.SpaceBackground
import com.example.chickeninviders.game.units.cosmic.Star
import com.example.chickeninviders.game.utils.Coord3D


@Preview
@Composable
fun GameWidgetPreview(){
    GameWidget(MainViewModel())
}


@Composable
fun GameWidget(mainViewModel: MainViewModel){


    SpawnScript.init()

    mainViewModel.addSpaceObject(initMySpace())

    val stars = createStars()

    mainViewModel.addSpaceObjects(stars)


    val meteors = createMeteors()



    mainViewModel.addSpaceObjects(meteors)


    val mySpaceCraft = SpawnScript.initMySpaceCraft()
    mainViewModel.addSpaceObject(mySpaceCraft)

    val chicken1Craft = SpawnScript.createChickenCraft()
    mainViewModel.addSpaceObject(chicken1Craft)


    val chicken2Craft = SpawnScript.createChickenCraft()
    mainViewModel.addSpaceObject(chicken2Craft)


    //val testGrain = initTestGrain()
   // mainViewModel.addSpaceObject(testGrain)

   // var spark = Spark(Coord3D(350f,400f,0f), MovementVector.ANY)
   // mainViewModel.addSpaceObject(spark)

   // val flame = SpawnScript.initTestFlame()
  //  mainViewModel.addSpaceObject(flame)


    //val sphere = SpawnScript.initTestSphere()
   // mainViewModel.addSpaceObject(sphere)

    mainViewModel.startTimer()

    SpaceCanvas(mainViewModel = mainViewModel)





}

@Composable
private fun initMySpace(): PhysicEntity {

    val imageBitmap = ImageBitmap.imageResource(id = R.drawable.space3)

    return SpaceBackground(imageBitmap)

}





private fun createStars(): MutableList<PhysicEntity> {
    val list = mutableListOf<PhysicEntity>()
    for (i in 0 .. 400){
        list.add(Star.create() )
    }
    list.sortByDescending { it.position.coord3D.z }
    return list
}

private fun createMeteors(): MutableList<PhysicEntity> {
    val list = mutableListOf<PhysicEntity>()
    for (i in 0 .. 50){
        list.add(Meteor.create() as Meteor)
    }
    list.sortByDescending { it.position.coord3D.z }
    return list
}

