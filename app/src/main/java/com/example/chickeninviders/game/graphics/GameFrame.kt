package com.example.chickeninviders.game.graphics

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size


object GameFrame {

    const val standartZ = 0f


    var widthPx: Int = 0
        private set
    var heightPx: Int = 0
        private set

    var widthDp: Float = 0f
        private set
    var heightDp: Float = 0f
        private set

    var centerXpix: Int = 0
        private set
    var centerYpix: Int = 0
        private set

    fun initialize(context: Context) {
        val displayMetrics: DisplayMetrics = context.resources.displayMetrics
        widthPx = displayMetrics.widthPixels
        heightPx = displayMetrics.heightPixels

        centerXpix = widthPx /2
        centerYpix = heightPx /2

        widthDp = widthPx / displayMetrics.density
        heightDp = heightPx / displayMetrics.density
    }


    fun isInFrame2D(myX: Float, myY: Float, size: Size): Boolean {
        return (myX > 0 && myX + size.width < widthPx && myY > 0 && myY + size.height < heightPx )
    }

    fun isInFrameWidth(myX: Float, size: Size): Boolean {
        val koef = widthPx * 0.2

        val minX = 0 - koef
        val maxX = widthPx + koef

        val myW = size.width

        val myPosMax = myX + myW

        val isInFrame = myX >= minX && myPosMax <= maxX



        return isInFrame
    }



}