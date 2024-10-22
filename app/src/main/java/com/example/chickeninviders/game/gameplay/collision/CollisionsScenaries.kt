package com.example.chickeninviders.game.gameplay.collision

import android.util.Log
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.bullets.ChickenBullet
import com.example.chickeninviders.game.units.bullets.PlayersBullet
import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import com.example.chickeninviders.game.units.warships.EvilChicken
import com.example.chickeninviders.game.units.warships.PlayerShip

fun checkParcelAmmoCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayerShip && pair.second is AmmoParcel || pair.second is PlayerShip && pair.first is AmmoParcel) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.ParcelAmmo
        )
    }
    return null
}


fun checkParcelLifeCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayerShip && pair.second is LifeParcel
        || pair.second is PlayerShip && pair.first is LifeParcel
    ) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.ParcelMedicine
        )
    }
    return null
}


fun checkFriendCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayerShip && pair.second is PlayersBullet
        || pair.second is PlayerShip && pair.first is PlayersBullet
        || pair.first is EvilChicken && pair.second is EvilChicken
        || pair.first is EvilChicken && pair.second is ChickenBullet
        || pair.second is EvilChicken && pair.first is ChickenBullet
    ) {
        return CollisionEvent(pair.first, pair.second, CollisionType.Ignoring)
    }
    return null
}

fun checkEnemyCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayerShip && pair.second is EvilChicken
        || pair.second is PlayerShip && pair.first is EvilChicken
        || pair.first is PlayerShip && pair.second is ChickenBullet
        || pair.second is PlayerShip && pair.first is ChickenBullet) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.Collision
        )
    }
    return null
}

fun checkTargetCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayersBullet && pair.second is EvilChicken
        || pair.second is PlayersBullet && pair.first is EvilChicken) {
        return CollisionEvent(
            pair.first, pair.second,  CollisionType.Target
        )
    }
    return null
}


fun checkAccidentCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayersBullet && pair.second is AmmoParcel
        || pair.second is PlayersBullet && pair.first is AmmoParcel
        || pair.first is PlayersBullet && pair.second is LifeParcel
        || pair.second is PlayersBullet && pair.first is LifeParcel) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.Accident
        )
    }
    return null
}