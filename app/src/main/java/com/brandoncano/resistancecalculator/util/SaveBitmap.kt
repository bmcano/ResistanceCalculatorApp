package com.brandoncano.resistancecalculator.util

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

/**
 * Job: Takes a bitmap and saves it to internal storage so a Uri can be created
 */
object SaveBitmap {

    fun execute(bitmap: Bitmap, context: Context): Uri? {
        val imagesFolder = File(context.cacheDir, "images")
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "resistor_image.jpg")
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            stream.flush()
            stream.close()
            return FileProvider.getUriForFile(
                context.applicationContext,
                "com.brandoncano.resistancecalculator.provider",
                file
            )
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return null
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }
}
