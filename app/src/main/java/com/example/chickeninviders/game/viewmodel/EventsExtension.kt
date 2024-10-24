package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.AMMO_PARCEL_PROBABILITY
import com.example.chickeninviders.LIFE_PARCEL_PROBABILITY
import com.example.chickeninviders.SHIELD_PARCEL_PROBABILITY
import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import com.example.chickeninviders.game.units.artifacts.ShieldParcel
import kotlin.random.Random

fun MainViewModel.spawnParcel(){

    val probabilityMultipleKoef = 100

    val parcelChanse = Random.nextInt(6)

    if(parcelChanse == 2) {//2

        val shotChanse = Random.nextInt(AMMO_PARCEL_PROBABILITY * probabilityMultipleKoef)
        if (shotChanse == 10) {

            val parc = AmmoParcel()
            _spaceObjects.value?.add(parc)
        }
    }

    if(parcelChanse == 3) {

        val shotChanse = Random.nextInt(SHIELD_PARCEL_PROBABILITY * probabilityMultipleKoef)
        if (shotChanse == 10) {

            val parc = ShieldParcel()
            _spaceObjects.value?.add(parc)
        }
    }

    if(parcelChanse == 4) {

        val shotChanse = Random.nextInt(LIFE_PARCEL_PROBABILITY * probabilityMultipleKoef)
        if (shotChanse == 10) {

            val parc = LifeParcel()
            _spaceObjects.value?.add(parc)
        }
    }

}