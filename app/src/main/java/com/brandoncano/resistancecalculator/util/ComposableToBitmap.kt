package com.brandoncano.resistancecalculator.util

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Picture

/**
 * Job: Takes a picture object which holds a composable (or multiple), and converts it to a bitmap
 */
object ComposableToBitmap {

    fun execute(picture: Picture): Bitmap {
        val bitmap = Bitmap.createBitmap(
            picture.width,
            picture.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        canvas.drawColor(android.graphics.Color.WHITE)
        canvas.drawPicture(picture)
        return bitmap
    }
}
