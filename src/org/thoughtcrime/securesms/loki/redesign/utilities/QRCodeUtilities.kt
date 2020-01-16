package org.thoughtcrime.securesms.loki.redesign.utilities

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter

object QRCodeUtilities {

    fun encode(data: String, size: Int, hasTransparentBackground: Boolean = true): Bitmap {
        try {
            val hints = hashMapOf( EncodeHintType.MARGIN to 1 )
            val result = QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size, hints)
            val bitmap = Bitmap.createBitmap(result.width, result.height, Bitmap.Config.ARGB_8888)
            for (y in 0 until result.height) {
                for (x in 0 until result.width) {
                    if (result.get(x, y)) {
                        bitmap.setPixel(x, y, Color.WHITE)
                    } else if (!hasTransparentBackground) {
                        bitmap.setPixel(x, y, Color.BLACK)
                    }
                }
            }
            return bitmap
        } catch (e: WriterException) {
            return Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888)
        }
    }
}