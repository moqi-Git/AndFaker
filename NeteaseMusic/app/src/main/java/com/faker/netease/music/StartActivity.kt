package com.faker.netease.music

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        startActivity(Intent(this, MainActivity::class.java))
    }
}
