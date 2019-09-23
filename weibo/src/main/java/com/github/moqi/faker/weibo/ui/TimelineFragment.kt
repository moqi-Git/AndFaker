package com.github.moqi.faker.weibo.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.NetRequest
import com.github.moqi.faker.plugins.toast
import com.github.moqi.faker.plugins.xlog
import com.github.moqi.faker.weibo.beans.WeiboStatus
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.github.moqi.faker.weibo.ui.base.BaseFragment
import com.github.moqi.faker.weibo.ui.tools.DividerDecoration
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_weibo_timeline.*

class TimelineFragment: BaseFragment() {
    private val request = NetRequest()

    private val mWeiboList = ArrayList<WeiboStatus.Statuse>()
    private val mWeiboAdapter = WeiboIntlMainAdapter(mWeiboList)

    private var page = 1 // 当前加载的最大页面

    override fun getLayoutId(): Int = R.layout.fragment_weibo_timeline

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
    }

    private fun initData(){
        mWeiboAdapter.cardClickEvent = { v, p->
            openDetail(mWeiboList[p], v.findViewById(R.id.item_weibo_intl_avatar))
        }
        mWeiboAdapter.avatarClickEvent = {v, p->
            openUserPage(mWeiboList[p].user.id.toLong())
        }

        weibo_rv_main.layoutManager = LinearLayoutManager(context)
        weibo_rv_main.addItemDecoration(DividerDecoration(0, 10, 0, 0))
        weibo_rv_main.adapter = mWeiboAdapter

        val token = activity!!.intent.getStringExtra("token")

        weibo_swap_refresh.setOnRefreshListener{
            refresh(token)
        }
        weibo_swap_refresh.setOnLoadMoreListener {
            loadmore(token)
        }

        weibo_swap_refresh.autoRefresh() //设置自动刷新动画和手动刷新一致
    }

    /* todo: 数据的获取尽快独立出去，封装彻底一点
     */
    private fun refresh(token: String) {
        page = 1 // 触发刷新的时候回到最初
        request.get(WeiboDataSource.URL_TIMELINE + "?access_token=$token&page=$page") { isSucc, result ->
            xlog(result)
            if (isSucc) {
                val timelineBean = Gson().fromJson(result, WeiboStatus.TimelineBean::class.java)
                timelineBean?.let {
                    mWeiboList.clear() // 此处等加入数据库缓存时再做对比，将新微博加在前面
                    mWeiboList.addAll(it.statuses)
                    activity?.runOnUiThread {
                        mWeiboAdapter.notifyDataSetChanged()
                        weibo_swap_refresh.finishRefresh()
                    }
                }
            } else {
                context?.toast("刷新失败：error($result)")
            }
        }
    }

    private fun loadmore(token: String){
        page++ // 每次loadmore都加载下一页
        request.get(WeiboDataSource.URL_TIMELINE + "?access_token=$token&page=$page") { isSucc, result ->
            xlog(result)
            if (isSucc) {
                val timelineBean = Gson().fromJson(result, WeiboStatus.TimelineBean::class.java)
                timelineBean?.let {
                    val newList = ArrayList<WeiboStatus.Statuse>()
                    newList.addAll(mWeiboList)// loadmore 的时候不清空当前list
                    newList.addAll(it.statuses)
                    mWeiboList.clear()
                    mWeiboList.addAll(newList)
                    activity?.runOnUiThread {
                        mWeiboAdapter.notifyDataSetChanged()
                        weibo_swap_refresh.finishLoadMore()
                    }
                }
            } else {
                context?.toast("加载失败：error($result)")
            }
        }
    }

    private fun openDetail(bean: WeiboStatus.Statuse, v: View){
        val i = Intent(context, WeiboDetailAct::class.java).apply {
            putExtra("name", bean.user.screen_name)
            putExtra("avatar", bean.user.avatar_large)
            putExtra("time", bean.created_at)
            putExtra("device", bean.source)
            putExtra("text", bean.text)
            putExtra("id", bean.id)

            putExtra("reposts", bean.reposts_count)
            putExtra("comments", bean.comments_count)
            putExtra("likes", bean.attitudes_count)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transitionName = "user_avatar"
            val options = ActivityOptions.makeSceneTransitionAnimation(activity, v, transitionName)
            startActivity(i, options.toBundle())
        } else {
            startActivity(i)
        }
    }

    private fun openUserPage(uid: Long){
        val i = Intent(context, WeiboUserActivity::class.java).apply {
            putExtra("uid", uid)
        }

        startActivity(i)
    }
}