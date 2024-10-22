package com.example.chickeninviders.game.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.chickeninviders.R


@Preview
@Composable
private fun DrawSrcImagePreview() {
    val imageBitmap = ImageBitmap.imageResource(R.drawable.ic_starship)
    DrawSrcImage(imageBitmap, 150.dp, 150.dp)
}


@Composable
 fun DrawSrcImage(imageBitmap: ImageBitmap, width: Dp, height: Dp) {

    Canvas(
        modifier = Modifier.size(width, height)
    ) {
        val canvasWidth = width.toPx().toInt()
        val canvasHeight = height.toPx().toInt()
        drawImage(
            image = imageBitmap,
            dstSize = IntSize(canvasWidth, canvasHeight)
        )
    }

}

