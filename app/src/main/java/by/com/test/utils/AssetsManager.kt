package by.com.test.utils

import android.app.Application
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Inject

class AssetsManager @Inject constructor(private val app: Application) {

    fun getAsset(filename: String): String {
        return app.assets.open(filename).bufferedReader().use {
            return@use it.readText()
        }
    }

}
