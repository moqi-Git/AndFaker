package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.NetRequest
import com.github.moqi.faker.plugins.loge
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
        request.get(WeiboDataSource.URL_TIMELINE + "?access_token=$token") { isSucc, result ->
            xlog(result)
            if (isSucc) {
                val timelineBean = Gson().fromJson(result, WeiboStatus.TimelineBean::class.java)
                timelineBean?.let {
                    mWeiboList.addAll(it.statuses)
                    runOnUiThread {
                        mWeiboAdapter.notifyDataSetChanged()
                        weibo_swap_refresh?.isRefreshing = false
                    }
                }
            } else {

            }
        }
    }

    private fun initView() {
        weibo_rv_main.layoutManager = LinearLayoutManager(this)
        weibo_rv_main.addItemDecoration(DividerDecoration(0, 10, 0, 0))
        weibo_rv_main.adapter = mWeiboAdapter

        weibo_swap_refresh.setOnRefreshListener{
            refresh()
        }
    }

    private fun setupTitleBar(){

    }
}
