package com.github.moqi.faker.weibo.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.NetRequest
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.spRead
import com.github.moqi.faker.plugins.xlog
import com.github.moqi.faker.weibo.beans.WeiboCommentBean
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.github.moqi.faker.weibo.ui.base.BaseFragment
import com.github.moqi.faker.weibo.ui.tools.DividerDecoration
import com.github.moqi.faker.weibo.ui.tools.ScreenHeightLayoutManager
import com.github.moqi.faker.weibo.ui.tools.ScreenInfo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_comment_list.*

/**
 *
 * created by reol at 2019-09-16
 */
class CommentListFragment : BaseFragment() {

    private val commentList = ArrayList<WeiboCommentBean.CommentContent>()
    private val commentAdapter = CommentListAdapter(commentList)

    private var page = 1

    override fun getLayoutId(): Int = R.layout.fragment_comment_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            weibo_comments_ll_root.isNestedScrollingEnabled = true
        }

        val cardHeight = arguments?.getInt("cardHeight")?:0
        val pageHeight = arguments?.getInt("pageHeight")?:0
        loge("ch=$cardHeight, ph=$pageHeight")

        val p = weibo_comments_rv.layoutParams
        loge("height=${pageHeight}")
        p.height = ScreenInfo.HEIGHT
        weibo_comments_rv.layoutParams = p

        weibo_comments_rv.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        weibo_comments_rv.addItemDecoration(DividerDecoration(0,1,0,0))
        weibo_comments_rv.adapter = commentAdapter
        refreshComments(page)
    }

    private fun refreshComments(page: Int) {
        val weiboId = activity?.intent?.getLongExtra("id", 0L)
        if (weiboId == null || weiboId == 0L) return

        val token = context!!.spRead("token")
        NetRequest().get(WeiboDataSource.URL_GET_COMMENTS + "?access_token=${token}&page=${page}&id=${weiboId}") { isSucc, result ->
            if (isSucc){
//                xlog(result)
                val comments = Gson().fromJson(result, WeiboCommentBean.Comment::class.java)
                commentList.clear()
                commentList.addAll(comments.comments)
                activity?.runOnUiThread {
                    commentAdapter.notifyDataSetChanged()
                }
            } else {
                loge(result)
            }
        }
    }
}