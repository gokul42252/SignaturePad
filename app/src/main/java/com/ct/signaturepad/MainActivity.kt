package com.ct.signaturepad

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.ct.signaturepad2.SignaturePad
import java.io.File
import java.io.FileOutputStream
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sign = findViewById<SignaturePad>(R.id.signaturePad2)
        sign.isDrawingCacheEnabled = true
        findViewById<View>(R.id.saveButton).setOnClickListener {
            val img = getBitmapFromView(sign, sign.width, sign.height)
            sign.isDrawingCacheEnabled = false
            storeToInternal(img)
        }

    }

    /**
     * Method to store the image to internal memory
     */
    private fun storeToInternal(img: Bitmap) {
        try {
            val extStorageDirectory = Environment.getExternalStorageDirectory().toString()
            val file = File(extStorageDirectory, "sign_" + Calendar.getInstance().timeInMillis + ".PNG")
            val outStream = FileOutputStream(file)
            img.compress(Bitmap.CompressFormat.PNG, 100, outStream)
            outStream.flush()
            outStream.close()
        } catch (e: Exception) {
            Log.d("", "")
        }
    }

    /**
     * Method to get bitmap image from view
     */
    private fun getBitmapFromView(v: View, width: Int, height: Int): Bitmap {
        val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val c = Canvas(b)
        v.layout(0, 0, v.layoutParams.width, v.layoutParams.height)
        v.draw(c)
        return b
    }


}
