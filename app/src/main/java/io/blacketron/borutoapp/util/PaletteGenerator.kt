package io.blacketron.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult

object PaletteGenerator {
    suspend fun convertImageUrlToBitmap(
        imageUrl: String,
        context: Context
    ): Bitmap? {
        val loader = ImageLoader(context = context)
        val request = ImageRequest.Builder(context = context)
            .data(imageUrl)
            .allowHardware(false)
            .build()
        val imageResult = loader.execute(request = request)
        return if (imageResult is SuccessResult) {
            (imageResult.drawable as BitmapDrawable).bitmap
        } else {
            null
        }
    }

    fun extractColorsFromBitmap(bitmap: Bitmap): Map<String, String> {
        return mapOf(
            "vibrant" to parseColorSwatch(
                swatch = Palette.from(bitmap).generate().vibrantSwatch
            ),
            "darkVibrant" to parseColorSwatch(
                swatch = Palette.from(bitmap).generate().darkVibrantSwatch
            ),
            "onDarkVibrant" to parseTextColor(
                swatch = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor
            )
        )
    }

    private fun parseColorSwatch(swatch: Palette.Swatch?): String {
        return if (swatch != null) {
            val parsedColor = Integer.toHexString(swatch.rgb)
            return "#$parsedColor"
        } else {
            "#000000"
        }
    }

    private fun parseTextColor(swatch: Int?): String {
        return if (swatch != null) {
            val parsedColor = Integer.toHexString(swatch)
            "#$parsedColor"
        } else {
            "#FFFFFF"
        }
    }
}