package com.example.chickeninviders.game.gameplay.collision

import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.bullets.ChickenBullet
import com.example.chickeninviders.game.units.bullets.PlayersBullet
import com.example.chickeninviders.game.units.artifacts.AmmoParcel
import com.example.chickeninviders.game.units.artifacts.LifeParcel
import com.example.chickeninviders.game.units.artifacts.Parcel
import com.example.chickeninviders.game.units.artifacts.ShieldParcel
import com.example.chickeninviders.game.units.warships.EvilChicken
import com.example.chickeninviders.game.units.warships.PlayerShip



fun checkParcelsCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    val ship: PlayerShip?
    val parcel: Parcel?

    if (pair.first is PlayerShip && pair.second is Parcel ) {
        ship = pair.first as PlayerShip
        parcel = pair.second as Parcel
    } else if (pair.second is PlayerShip && pair.first is Parcel) {
        ship = pair.second as PlayerShip
        parcel = pair.first as Parcel
    } else {
        ship = null
        parcel = null
    }

    if (ship != null && parcel != null) {

        val collisionType = when (parcel) {
            is AmmoParcel -> {
                CollisionType.ParcelAmmo
            }

            is LifeParcel -> {
                CollisionType.ParcelMedicine
            }

            else -> {
                CollisionType.ParcelShield
            }
        }

        return CollisionEvent(
            pair.first, pair.second, collisionType
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


    val ship: PlayerShip?
    val chicken: Any?

    if (pair.first is PlayerShip && (pair.second is EvilChicken || pair.second is ChickenBullet)) {
        ship = pair.first as PlayerShip
        chicken = pair.second
    } else if (pair.second is PlayerShip && (pair.first is EvilChicken || pair.first is ChickenBullet)) {
        ship = pair.second as PlayerShip
        chicken = pair.first
    } else {
        ship = null
        chicken = null
    }


    if (ship != null && chicken != null)
    {

        val collisionType = if (ship.hasGuardSphere()) {
            CollisionType.CollisionWithSafeGuard
        } else {
            CollisionType.Collision
        }
        return CollisionEvent(
            pair.first, pair.second, collisionType
        )
    }
    return null
}

fun checkTargetCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayersBullet && pair.second is EvilChicken
        || pair.second is PlayersBullet && pair.first is EvilChicken
    ) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.Target
        )
    }
    return null
}


fun checkAccidentCollision(pair: Pair<PhysicEntity, PhysicEntity>): CollisionEvent? {
    if (pair.first is PlayersBullet && pair.second is AmmoParcel
        || pair.second is PlayersBullet && pair.first is AmmoParcel
        || pair.first is PlayersBullet && pair.second is LifeParcel
        || pair.second is PlayersBullet && pair.first is LifeParcel
        || pair.first is PlayersBullet && pair.second is ShieldParcel
        || pair.second is PlayersBullet && pair.first is ShieldParcel
    ) {
        return CollisionEvent(
            pair.first, pair.second, CollisionType.Accident
        )
    }
    return null
}