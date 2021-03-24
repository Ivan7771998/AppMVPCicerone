package com.dev777popov.appmvpcicerone.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    fun getDate(dateStr: String?): String {
        var normalDate = ""
        try {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
            val formatterNormal = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val mDate = formatter.parse(dateStr!!)
            normalDate = formatterNormal.format(mDate!!)
        } catch (e: Exception) {
            Log.e("mDate", e.toString())
        }
        return normalDate
    }
}