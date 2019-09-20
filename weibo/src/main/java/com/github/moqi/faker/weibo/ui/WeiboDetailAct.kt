package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.MenuItem
import android.view.View
import androidx.core.widget.NestedScrollView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.pxcdp
import com.github.moqi.faker.plugins.replaceFragment
import com.github.moqi.faker.plugins.toast
import com.github.moqi.faker.weibo.datasource.tools.formTimeString
import com.github.moqi.faker.weibo.ui.tools.ScreenInfo
import kotlinx.android.synthetic.main.activity_weibo_detail.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.*
import kotlinx.android.synthetic.main.item_weibo_intl_main.view.*

class WeiboDetailAct : AppCompatActivity() {

    private var weiboCardHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_detail)

        setSupportActionBar(weibo_detail_toolbar)
        setupTitle()

        initData()
    }

    private fun initData() {
        val name = intent.getStringExtra("name")
        val avatar = intent.getStringExtra("avatar")
        val createTime = intent.getStringExtra("time")
        val device = intent.getStringExtra("device")
        val text = intent.getStringExtra("text")

        val numRepost = intent.getIntExtra("reposts", 0)
        val numComment = intent.getIntExtra("comments", 0)
        val numLike = intent.getIntExtra("likes", 0)
        weibo_detail_tab_comment.text = "Comments ${numComment}"
        weibo_detail_pin_tab_comment.text = "Comments ${numComment}"
        weibo_detail_tab_like.text = "Likes ${numLike}"
        weibo_detail_pin_tab_like.text = "Likes ${numLike}"
        weibo_detail_tab_repost.text = "Likes ${numRepost}"
        weibo_detail_pin_tab_repost.text = "Likes ${numRepost}"

        item_weibo_intl_name.text = name
        item_weibo_intl_time.text = formTimeString(createTime)
        item_weibo_intl_device.text = Html.fromHtml(device)
        item_weibo_intl_text.text = text
        Glide.with(this)
            .load(avatar)
            .apply(RequestOptions().circleCrop())
            .into(item_weibo_intl_avatar)

        // 考虑到数据的量，更多数据
        measureContent()
    }

    private fun measureContent() {
        // 计算微博卡片高度，用于判断滑动位置
        item_weibo_intl_card.post {
            weiboCardHeight = item_weibo_intl_card.height + pxcdp(12f)
            loge(weiboCardHeight.toString())

            val commentFragment = CommentListFragment().apply {
                arguments = Bundle().apply {
                    putInt("cardHeight", weiboCardHeight)
                    putInt("pageHeight", ScreenInfo.HEIGHT - supportActionBar!!.height - pxcdp(55f))
                }
            } // fixme:暂时测试用
            replaceFragment(R.id.weibo_detail_act_container, commentFragment)

            loge("weibo_detail_scrollbar height = ${weibo_detail_scrollbar.height}")

            weibo_detail_scrollbar.parentScrollDistance = weiboCardHeight
            weibo_detail_scrollbar.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
                loge("sx=$scrollX, sy=$scrollY")

                if (scrollY > weiboCardHeight){
                    weibo_detail_pin_tab.visibility = View.VISIBLE
                } else {
                    weibo_detail_pin_tab.visibility = View.GONE
                }
            }
        }
    }

    private fun setupTitle() {
        val actionBar = supportActionBar ?: return
        actionBar.apply {
            title = "微博详情"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
