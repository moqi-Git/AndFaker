package com.github.moqi.faker.weibo.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.NetRequest
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.toast
import com.github.moqi.faker.plugins.xlog
import com.github.moqi.faker.weibo.beans.WeiboStatus

import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.github.moqi.faker.weibo.ui.tools.DividerDecoration
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weibo_intl_main.*

class WeiboIntlMainAct : AppCompatActivity() {

    private val request = NetRequest()

    private val mWeiboList = ArrayList<WeiboStatus.Statuse>()
    private val mWeiboAdapter = WeiboIntlMainAdapter(mWeiboList)

    private var page = 1 // 当前加载的最大页面

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_intl_main)

        setSupportActionBar(weibo_toolbar)
        setupTitleBar()

        initView()

        refresh()
    }

    private fun refresh() {
        val token = intent.getStringExtra("token")
        request.get(WeiboDataSource.URL_TIMELINE + "?access_token=$token&page=$page") { isSucc, result ->
            xlog(result)
            if (isSucc) {
                val timelineBean = Gson().fromJson(result, WeiboStatus.TimelineBean::class.java)
                timelineBean?.let {
                    mWeiboList.clear()
                    mWeiboList.addAll(it.statuses)
                    runOnUiThread {
                        mWeiboAdapter.notifyDataSetChanged()
                        weibo_swap_refresh?.isRefreshing = false
                    }
                }
            } else {
                toast("刷新失败：error($result)")
            }
        }
    }

    //todo：实现 loadmore

    private fun initView() {
        mWeiboAdapter.cardClickEvent = { v, p->
            openDetail(mWeiboList[p], v.findViewById(R.id.item_weibo_intl_avatar))
        }

        weibo_rv_main.layoutManager = LinearLayoutManager(this)
        weibo_rv_main.addItemDecoration(DividerDecoration(0, 10, 0, 0))
        weibo_rv_main.adapter = mWeiboAdapter

        weibo_swap_refresh.setOnRefreshListener{
            refresh()
        }
    }

    private fun openDetail(bean: WeiboStatus.Statuse, v: View){
        val i = Intent(this, WeiboDetailAct::class.java).apply {
            putExtra("name", bean.user.screen_name)
            putExtra("avatar", bean.user.avatar_large)
            putExtra("text", bean.text)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transitionName = "user_avatar"
            val options = ActivityOptions.makeSceneTransitionAnimation(this, v, transitionName)
            startActivity(i, options.toBundle())
        } else {
            startActivity(i)
        }
    }

    private fun setupTitleBar(){

    }
}
