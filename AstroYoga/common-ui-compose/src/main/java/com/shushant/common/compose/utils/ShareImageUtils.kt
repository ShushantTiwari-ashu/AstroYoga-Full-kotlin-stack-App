@file:Suppress("DEPRECATION")

package com.shushant.common.compose.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.view.ViewGroup
import java.io.ByteArrayOutputStream

object ShareImageUtils {
    fun convertViewToImage(view: ViewGroup, imageWidth: Int, imageHeight: Int): Bitmap? {
        val returnedBitmap = Bitmap.createBitmap(imageWidth, imageHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnedBitmap)
        val bgDrawable = view.background
        if (bgDrawable != null) bgDrawable.draw(canvas) else canvas.drawColor(Color.WHITE)
        view.draw(canvas)
        return returnedBitmap
    }

    fun invokeShare(context: Context, bitmap: Bitmap?) {
        val intent = Intent(Intent.ACTION_SEND).setType("image/*")
        val bytes = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            context.contentResolver,
            bitmap,
            "temp_image",
            null
        )
        val uri = Uri.parse(path)
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        context.startActivity(intent)
    }
}
