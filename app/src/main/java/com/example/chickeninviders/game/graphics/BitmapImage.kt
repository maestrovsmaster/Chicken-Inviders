package com.example.chickeninviders.game.graphics

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas


fun DrawScope.drawImageBitmap(imageBitmap: ImageBitmap, offset: Offset, size: Size) {
    val imgWidth = imageBitmap.width
    val imgHeight = imageBitmap.height
    val scaleX = size.width / imgWidth
    val scaleY = size.height / imgHeight

    this.drawIntoCanvas { canvas: Canvas ->
        canvas.save()
        canvas.translate(offset.x, offset.y)
        canvas.scale(scaleX, scaleY)
        canvas.drawImage(
            image = imageBitmap,
            Offset(0f, 0f),
            Paint()
        )
        canvas.restore()
    }
}

