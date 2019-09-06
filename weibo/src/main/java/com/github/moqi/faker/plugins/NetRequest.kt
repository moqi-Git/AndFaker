package com.github.moqi.faker.plugins

import okhttp3.*
import java.io.IOException

/**
 *
 * created by reol at 2019-09-02
 */

class NetRequest(){

    private val client = OkHttpClient()

    fun get(url: String, cb:(Boolean, String)->Unit){
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                cb(false, e.message?:"unknown")
            }

            override fun onResponse(call: Call, response: Response) {
                cb(true, response.body?.string()?:"{}")
            }
        })
    }

    fun post(url: String, jsonParams: String){

    }
}