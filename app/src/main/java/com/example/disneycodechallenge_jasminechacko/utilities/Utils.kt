package com.example.disneycodechallenge_jasminechacko.utilities

import android.content.Context
import java.io.IOException
import java.io.InputStream

object Utils {
    fun getJsonFromAssets(context: Context, fileName: String): String? {
        val jsonString: String = try {
            val mInputStream: InputStream = context.assets.open(fileName)
            val size: Int = mInputStream.available()
            val buffer = ByteArray(size)
            mInputStream.read(buffer)
            mInputStream.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }

        return jsonString
    }

//    fun parseJSON() {
//        Gson().fromJson(getJsonFromAssets(context, Utils::class.java))
//    }
}