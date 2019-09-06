package com.github.moqi.faker.plugins

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlin.math.max

/**
 *
 * created by reol at 2019-09-03
 */

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun loge(text: String){
    Log.e("asdfg", text)
}

fun Context.spWrite(key: String, value: String, file: String = "MAKI_KEY"){
    val sp = getSharedPreferences(file, 0)
    sp.edit().putString(key, value).apply()
}

fun Context.spRead(key: String, file: String = "MAKI_KEY"): String{
    val sp = getSharedPreferences(file, 0)
    return sp.getString(key, "")
}

fun xlog(text: String){
    val maxLength = 2000

    var start = 0
    var length = text.length

    while (true){
        if (length > maxLength){
            loge(text.substring(start, start + maxLength))
            start += maxLength
            length -= maxLength
        } else {
            loge(text.substring(start, start + length))
            break
        }
    }
}