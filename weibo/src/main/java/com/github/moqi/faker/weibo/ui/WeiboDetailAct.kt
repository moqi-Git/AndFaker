package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.toast
import kotlinx.android.synthetic.main.activity_weibo_detail.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.view.*

class WeiboDetailAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_detail)

        setSupportActionBar(weibo_detail_toolbar)

        initData()
    }

    private fun initData(){
        val name = intent.getStringExtra("name")
        val avatar = intent.getStringExtra("avatar")
        val text = intent.getStringExtra("text")

        item_weibo_intl_name.text = name
        item_weibo_intl_text.text = text
        Glide.with(this)
            .load(avatar)
            .apply(RequestOptions().circleCrop())
            .into(item_weibo_intl_avatar)
    }
}
