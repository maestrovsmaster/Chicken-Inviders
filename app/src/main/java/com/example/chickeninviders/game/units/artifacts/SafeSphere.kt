package com.example.chickeninviders.game.units.artifacts

import android.util.Log
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
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
    var colorSet: SparkColorSet = SparkColorSet(Color.White, Color.Yellow, Color.Red)

) : PhysicEntity(position, Size(1f, 1f)) {


    data class SphereDot(var r: Double, var fi: Double, var color: Color = Color.White)


    var dots: MutableList<SphereDot> = generateDotsCloud(capacity, radius)


    companion object {


        fun generateDotsCloud(
            capacity: Int, radius: Float,
        ): MutableList<SphereDot> {

            val list = mutableListOf<SphereDot>()

            // val countFi = 100
            // val stepFi = 360 / countFi


            // 360 = 100
            //  fi = x
            //  x = fi*100/360


            for (i in 0..45) {

                val fi = i * 8.0

                val prc = fi * 100 / 360

                Log.d("SuperSphere", "procent = $prc")
                val colorRed =  prc / 100f
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


    override fun draw(drawScope: DrawScope, cameraOffset: Coord3D) {

        val transformedCoords = this.transformOffset(cameraOffset)
        val coord2D =  transformPerspective(transformedCoords)
        dots.map { dot ->
             val (x, y) = polarToCartesian(radius = dot.r, dot.fi.degreesToRadians())
            drawScope.drawCircle( dot.color,
                radius = 3.0f, center = androidx.compose.ui.geometry.Offset(coord2D.x+x.toFloat(), coord2D.y+y.toFloat())
            )

            val (x2, y2) = polarToCartesian(radius = dot.r-10, dot.fi.degreesToRadians())
            drawScope.drawCircle( dot.color,
                radius = 2.0f, center = androidx.compose.ui.geometry.Offset(coord2D.x+x2.toFloat(), coord2D.y+y2.toFloat())
            )

            val (x3, y3) = polarToCartesian(radius = dot.r-20, dot.fi.degreesToRadians())
            drawScope.drawCircle( dot.color,
                radius = 1.5f, center = androidx.compose.ui.geometry.Offset(coord2D.x+x3.toFloat(), coord2D.y+y3.toFloat())
            )
        }


    }

    override fun update() {
        this.move()

        dots.map { dot ->
            var newFi = dot.fi + 1
            if(newFi > 360) newFi -=360
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


}
