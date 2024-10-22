package com.example.chickeninviders.game.gameplay.collision

import com.example.chickeninviders.game.physic.PhysicEntity

data class CollisionEvent(val first: PhysicEntity, val second: PhysicEntity, val collisionType: CollisionType)
