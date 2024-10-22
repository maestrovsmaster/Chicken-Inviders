package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import kotlin.random.Random

fun MainViewModel.spawnParcel(){

    val parcelChanse = Random.nextInt(6)

    if(parcelChanse == 2) {//2

        val shotChanse = Random.nextInt(523)
        if (shotChanse == 10) {

            val parc = AmmoParcel()
            _spaceObjects.value?.add(parc)
        }
    }

    if(parcelChanse == 4) {

        val shotChanse = Random.nextInt(1230)
        if (shotChanse == 10) {

            val parc = LifeParcel()
            _spaceObjects.value?.add(parc)
        }
    }

}