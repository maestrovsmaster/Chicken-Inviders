import android.util.Log
import com.example.chickeninviders.game.gameplay.collision.CollisionEvent
import com.example.chickeninviders.game.gameplay.collision.CollisionType
import com.example.chickeninviders.game.gameplay.collision.checkAccidentCollision
import com.example.chickeninviders.game.gameplay.collision.checkEnemyCollision
import com.example.chickeninviders.game.gameplay.collision.checkFriendCollision
import com.example.chickeninviders.game.gameplay.collision.checkParcelAmmoCollision
import com.example.chickeninviders.game.gameplay.collision.checkParcelLifeCollision
import com.example.chickeninviders.game.gameplay.collision.checkTargetCollision
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.bullets.Bullet
import com.example.chickeninviders.game.utils.Coord3D
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

// CollisionUtils.kt
//data class Coord3D(val x: Int, val y: Int, val z: Int)
data class Position(val coord3D: Coord3D)
data class Size(val width: Int, val height: Int, val depth: Int)

fun checkCollision(
    craft1: PhysicEntity,
    craft2: PhysicEntity,
    penetrationCoefficient: Double
): Boolean {

    val x1 = craft1.position.coord3D.x
    val y1 = craft1.position.coord3D.y

    val x2 = craft2.position.coord3D.x
    val y2 = craft2.position.coord3D.y


    val size1 = craft1.size
    val size2 = craft2.size

    // val koef = 0.56

    val d1 = sqrt(size1.width * size1.width + size1.height * size1.height)
    val d2 = sqrt(size2.width * size2.width + size2.height * size2.height)
    val c1 = abs(x1 - x2) < (size1.width + size2.width) / 2 * penetrationCoefficient
    val c2 = abs(y1 - y2) < (size1.height + size2.height) / 2
    val c3 = sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2)) < d1 + d2
    return c1 && c2 && c3
}

fun detectCollisions(spaceCrafts: List<PhysicEntity>): List<Pair<PhysicEntity, PhysicEntity>> {
    val collisions = mutableListOf<Pair<PhysicEntity, PhysicEntity>>()

    val koef = 0.54
    for (i in spaceCrafts.indices) {
        for (j in i + 1 until spaceCrafts.size) {
            val obj1 = spaceCrafts[i]
            val obj2 = spaceCrafts[j]



            if (obj1.conflictable && obj2.conflictable) {

                if (obj1 is Bullet && obj2 is Bullet) {
                    continue
                }

                if (checkCollision(obj1, obj2, koef)) {
                    collisions.add(Pair(spaceCrafts[i], spaceCrafts[j]))
                }
            }
        }
    }

    return collisions
}


fun sortCollisions(objects: List<PhysicEntity>): List<CollisionEvent> {

    val events = mutableListOf<CollisionEvent>()

    val collisions = detectCollisions(objects)

    if (collisions.isNullOrEmpty()) return events
    collisions.map { collision ->

        checkFriendCollision(collision)?.let {
            events.add(it)
        }
        checkParcelAmmoCollision(collision)?.let {
            events.add(it)
        }
        checkParcelLifeCollision(collision)?.let {
            events.add(it)
        }

        checkEnemyCollision(collision)?.let {
            events.add(it)
        }
        checkTargetCollision(collision)?.let {
            events.add(it)
        }
        checkAccidentCollision(collision)?.let {
            events.add(it)
        }


    }


    return events
}

