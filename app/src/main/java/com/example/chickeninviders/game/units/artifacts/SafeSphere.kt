package com.example.chickeninviders.game.units.artifacts

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.example.chickeninviders.GLOBAL_TIMER_DELAY
import com.example.chickeninviders.SHIELD_ACTIVE_TIME
import com.example.chickeninviders.game.graphics.transformPerspective
import com.example.chickeninviders.game.physic.PhysicEntity
import com.example.chickeninviders.game.units.flame.Dot
import com.example.chickeninviders.game.units.flame.SparkColorSet
import com.example.chickeninviders.game.units.flame.Speed
import com.example.chickeninviders.game.utils.Coord3D
import com.example.chickeninviders.game.utils.Position
import com.example.chickeninviders.utils.degreesToRadians
import com.example.chickeninviders.utils.polarToCartesian
import com.example.chickeninviders.utils.sphericalToCartesian

class SafeSphere(
    override var position: Position,
    var radius: Float = 130f,
    var capacity: Int = 20,
    var speed: Speed = Speed.NORMAL,
    var colorSet: SparkColorSet = SparkColorSet(Color.White, Color.Yellow, Color.Red),
    val totalTime: Long = SHIELD_ACTIVE_TIME,
    val onCompeted: () -> Unit = {},

) : PhysicEntity(position, Size(1f, 1f)) {


    data class SphereDot(var r: Double, var fi: Double, var color: Color = Color.White)

    var dots: MutableList<SphereDot> = generateDotsCloud(capacity, radius)


    companion object {
        val nCount: Int = 45

        fun generateDotsCloud(
            capacity: Int, radius: Float,
        ): MutableList<SphereDot> {

            val list = mutableListOf<SphereDot>()


            for (i in 0..nCount) {

                val fi = i * 360 / nCount

                val prc = fi * 100 / 360

                Log.d("SuperSphere", "procent = $prc")
                val colorRed = prc / 100f
                val colorGreen = 1 - colorRed
                val colorBlue = 1f

                val color = Color(colorRed.toFloat(), colorGreen.toFloat(), colorBlue)

                list.add(
                    SphereDot(
                        radius.toDouble(), fi.toDouble(), color
                    )
                )

            }


            return list
        }


    }

    val L: Long = GLOBAL_TIMER_DELAY

    val totalFrames = (totalTime / L).toInt() // Загальна кількість кадрів

    val dotsPerFrame: Float = nCount.toFloat() / totalFrames

    var decreaseIndex = 0


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {

        Log.d("PlayerShip", " SafeSphere draw")

        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D = transformPerspective(transformedCoords)


        //Regular dots
        dots.map { dot ->

            val (x2, y2) = polarToCartesian(radius = dot.r - 10, dot.fi.degreesToRadians())
            drawScope.drawCircle(
                dot.color,
                radius = 2.0f,
                center = androidx.compose.ui.geometry.Offset(
                    coord2D.x + x2.toFloat(),
                    coord2D.y + y2.toFloat()
                )
            )


            val (x3, y3) = polarToCartesian(radius = dot.r - 20, dot.fi.degreesToRadians())
            drawScope.drawCircle(
                dot.color,
                radius = 1.5f,
                center = androidx.compose.ui.geometry.Offset(
                    coord2D.x + x3.toFloat(),
                    coord2D.y + y3.toFloat()
                )
            )

        }

        //Progress dots
        // Рахуємо скільки точок повинно зникнути до цього моменту
        val toIndex = maxOf(nCount - (decreaseIndex * dotsPerFrame).toInt() - 1, 0)

        for (i in 0..toIndex) {
            val dot = dots[i]

            val (x, y) = polarToCartesian(radius = dot.r, dot.fi.degreesToRadians())

            drawScope.drawCircle(
                dot.color,
                radius = 3.0f,
                center = androidx.compose.ui.geometry.Offset(
                    coord2D.x + x.toFloat(),
                    coord2D.y + y.toFloat()
                )
            )





        }

        decreaseIndex++

        if (decreaseIndex >= totalFrames) {
            onCompeted()
        }


    }

    override fun update() {
        this.move()

        dots.map { dot ->
            var newFi = dot.fi + 1
            if (newFi > 360) newFi -= 360
            dot.fi = newFi
        }

        /*  val sparksToRemove = mutableListOf<Spark>()
          val sparksToAdd = mutableListOf<Spark>()

          sparks.forEach {
              it.move()
              if (it.shouldRemove()) {
                  sparksToRemove.add(it)
                  if (repeat) {
                      val spark = generateSpark(position.coord3D, width, length, speed, colorSet)
                      sparksToAdd.add(spark)
                  }
              }
          }

          sparks.removeAll(sparksToRemove)
          sparks.addAll(sparksToAdd)*/
    }

    override fun shouldRemove(): Boolean {
        return dots.size <= 10
    }

    override fun name() = "SafeSphere"


}
