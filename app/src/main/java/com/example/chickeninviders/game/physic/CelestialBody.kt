package com.example.chickeninviders.game.physic
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import kotlin.random.Random


abstract class CelestialBody(override var position: Position, override var size: Size, color: Color) : PhysicEntity(
    position,
    size,
) {

    companion object {
        fun generateRandomCoord(maxX: Float,maxY: Float,maxZ: Float): Coord3D {
            val x = Random.nextFloat() * maxX - maxX/2
            val y = Random.nextFloat() * maxY - maxY/2
            val z =   Random.nextFloat() * maxZ  + 2//- maxZ/2
            return Coord3D(x,y,z)
        }

        fun generateRandomColor(): Color {
            val red = Random.nextInt(156)+100
            val green = Random.nextInt(156)+100
            val blue = Random.nextInt(156)+100
            return Color(red, green, blue)
        }
    }




}


interface CelestialBodyFactory {
    fun create(): CelestialBody
}