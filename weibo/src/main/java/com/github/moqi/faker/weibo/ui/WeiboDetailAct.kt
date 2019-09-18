package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.replaceFragment
import com.github.moqi.faker.plugins.toast
import com.github.moqi.faker.weibo.datasource.tools.formTimeString
import kotlinx.android.synthetic.main.activity_weibo_detail.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.view.*

class WeiboDetailAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_detail)

        setSupportActionBar(weibo_detail_toolbar)
        setupTitle()

        initData()
    }

    private fun initData(){
        val name = intent.getStringExtra("name")
        val avatar = intent.getStringExtra("avatar")
        val createTime = intent.getStringExtra("time")
        val device = intent.getStringExtra("device")
        val text = intent.getStringExtra("text")

        item_weibo_intl_name.text = name
        item_weibo_intl_time.text = formTimeString(createTime)
        item_weibo_intl_device.text = Html.fromHtml(device)
        item_weibo_intl_text.text = text
        Glide.with(this)
            .load(avatar)
            .apply(RequestOptions().circleCrop())
            .into(item_weibo_intl_avatar)

        // 考虑到数据的量，更多数据

        val commentFragment = CommentListFragment() // fixme:暂时测试用
        replaceFragment(R.id.weibo_detail_act_container, commentFragment)
    }

    private fun setupTitle(){
        val actionBar = supportActionBar?:return
        actionBar.apply {
            title = "微博详情"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
