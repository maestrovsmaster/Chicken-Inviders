package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import com.example.chickeninviders.game.units.artifacts.ShieldParcel
import kotlin.random.Random

fun MainViewModel.spawnParcel(){

    val parcelChanse = Random.nextInt(6)

    if(parcelChanse == 2) {//2

        val shotChanse = Random.nextInt(520)
        if (shotChanse == 10) {

            val parc = AmmoParcel()
            _spaceObjects.value?.add(parc)
        }
    }

    if(parcelChanse == 3) {

        val shotChanse = Random.nextInt(600)
        if (shotChanse == 10) {

            val parc = ShieldParcel()
            _spaceObjects.value?.add(parc)
        }
    }

    if(parcelChanse == 4) {

        val shotChanse = Random.nextInt(1352)
        if (shotChanse == 10) {

            val parc = LifeParcel()
            _spaceObjects.value?.add(parc)
        }
    }

}