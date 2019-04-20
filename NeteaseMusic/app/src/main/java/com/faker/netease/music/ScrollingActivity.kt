package com.faker.netease.music

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.faker.netease.music.widget.SelectListSheet
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)
        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            val sheet = SelectListSheet()
            sheet.show(supportFragmentManager, "songs")
        }
    }
}
