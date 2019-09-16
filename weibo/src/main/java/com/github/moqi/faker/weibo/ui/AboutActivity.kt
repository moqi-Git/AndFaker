package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.moqi.faker.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        actionBar?.title = "关于"
    }
}
