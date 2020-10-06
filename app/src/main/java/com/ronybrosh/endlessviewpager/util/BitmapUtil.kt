package com.ronybrosh.endlessviewpager.util

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap

object BitmapUtil {
    fun createMaskedDrawable(
        resources: Resources,
        bottomDrawableResourceId: Int,
        topDrawableResourceId: Int
    ): BitmapDrawable? {
        val bottomBitmap = createBitmap(resources, bottomDrawableResourceId) ?: return null
        val topBitmap = createBitmap(resources, topDrawableResourceId, bottomBitmap) ?: return null
        val overlay =
            Bitmap.createBitmap(bottomBitmap.width, bottomBitmap.height, bottomBitmap.config);

        val paint = Paint()
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP)

        val canvas = Canvas(overlay)
        canvas.drawBitmap(bottomBitmap, Matrix(), null)
        canvas.drawBitmap(topBitmap, Matrix(), paint)
        bottomBitmap.recycle()
        topBitmap.recycle()

        return BitmapDrawable(resources, overlay)
    }

    private fun createBitmap(resources: Resources, resourceId: Int): Bitmap? {
        val drawable = ResourcesCompat.getDrawable(resources, resourceId, null)
        return drawable?.toBitmap()
    }

    private fun createBitmap(
        resources: Resources,
        resourceId: Int,
        bitmap: Bitmap
    ): Bitmap? {
        val drawable = ResourcesCompat.getDrawable(resources, resourceId, null)
        return drawable?.toBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
    }
}