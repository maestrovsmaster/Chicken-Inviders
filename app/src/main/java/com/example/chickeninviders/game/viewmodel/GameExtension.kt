package com.example.chickeninviders.game.viewmodel

import com.example.chickeninviders.game.gameplay.SpawnScript
import com.example.chickeninviders.game.gameplay.collision.CollisionType
import com.example.chickeninviders.game.physic.ArtificialObject
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.bullets.Bullet
import com.example.chickeninviders.game.units.flame.BangType
import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import com.example.chickeninviders.game.units.warships.EvilChicken
import sortCollisions


internal fun MainViewModel.updateGameState() {
    updateCamera()
    moveObjects()
    checkForCollisions()
    recreateObjects()
    removeObjects()
    checkChickensShots()

    //Random events
    spawnParcel()
}


internal fun MainViewModel.moveObjects() {
    _spaceObjects.value?.forEach { obj ->
        obj.update()
    }
}

internal fun MainViewModel.recreateObjects() {
    val objectsForRecreate = mutableListOf<PhysicEntity>()
    _spaceObjects.value?.forEach { obj ->
        if (obj.shouldRecreate()) {
            if (obj is EvilChicken) {
                val newObj = SpawnScript.createChickenCraft()
                objectsForRecreate.add(newObj)
            }
        }
    }
    _spaceObjects.value?.addAll(objectsForRecreate)
}

internal fun MainViewModel.removeObjects() {
    val objectsForRemove = mutableListOf<PhysicEntity>()
    _spaceObjects.value?.forEach { obj ->
        val shouldRemove = obj.shouldRemove()
        if (shouldRemove) {
            objectsForRemove.add(obj)
        }
    }
    _spaceObjects.value?.removeAll(objectsForRemove)
}

internal fun MainViewModel.checkChickensShots() {
    val newBullets = mutableListOf<Bullet>()
    spaceObjects.value?.forEach { obj ->
        if (obj is EvilChicken) {
            obj.checkRandomShot()?.let { bullet ->
                newBullets.add(bullet)
            }
        }
    }
    _spaceObjects.value?.addAll(newBullets)
}

internal fun MainViewModel.checkForCollisions() {
    val collisionsItems = mutableListOf<PhysicEntity>()

    _spaceObjects.value?.let { spaceObjs ->
        collisionsItems.addAll(spaceObjs.filterIsInstance<ArtificialObject>())
    }


    val collisionEvents = sortCollisions(collisionsItems)

    val bangsForAdd = mutableListOf<PhysicEntity>()
    collisionEvents.map {

        when (it.collisionType) {
            CollisionType.Ignoring -> {}
            CollisionType.Collision -> {
                val newValue =  10
                _ammo.postValue(newValue)

                removeLife()

                if (it.first is ArtificialObject) {
                    bangsForAdd.add(it.first.bang(BangType.Bang))
                }
                if (it.second is ArtificialObject) {
                    bangsForAdd.add(it.second.bang(BangType.Bang))
                }
            }

            CollisionType.ParcelAmmo -> {
                val newValue = _ammo.value!! + 10
                _ammo.postValue(newValue)
                if (it.first is AmmoParcel) {
                    bangsForAdd.add(it.first.bang(BangType.Salut))
                }
                if (it.second is AmmoParcel) {
                    bangsForAdd.add(it.second.bang(BangType.Salut))
                }
            }

            CollisionType.ParcelMedicine ->{
                addLife()
                if (it.first is LifeParcel) {
                    bangsForAdd.add(it.first.bang(BangType.Salut))
                }
                if (it.second is LifeParcel) {
                    bangsForAdd.add(it.second.bang(BangType.Salut))
                }
            }


            CollisionType.Target -> {

                val newValue = _ammo.value!! + 5
                _ammo.postValue(newValue)
                if (it.first is EvilChicken) {
                    bangsForAdd.add(it.first.bang(BangType.Target))
                }
                if (it.second is EvilChicken) {
                    bangsForAdd.add(it.second.bang(BangType.Target))
                }

            }

            CollisionType.Accident -> {
                if (it.first is AmmoParcel) {
                    bangsForAdd.add(it.first.bang(BangType.Accident))
                }
                if (it.second is AmmoParcel) {
                    bangsForAdd.add(it.second.bang(BangType.Accident))
                }
                if (it.first is LifeParcel) {
                    bangsForAdd.add(it.first.bang(BangType.Accident))
                }
                if (it.second is LifeParcel) {
                    bangsForAdd.add(it.second.bang(BangType.Accident))
                }
            }


            else -> {}
        }


    }
    if (bangsForAdd.isNotEmpty())
        _spaceObjects.value?.addAll(bangsForAdd)


}

