package com.github.moqi.faker.weibo.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.weibo.beans.WeiboStatus
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.github.moqi.faker.weibo.datasource.tools.formTimeString
import com.github.moqi.faker.weibo.datasource.tools.getWeiboType
import com.github.moqi.faker.weibo.ui.tools.DividerDecoration
import com.github.moqi.faker.weibo.ui.tools.ScreenInfo
import kotlinx.android.synthetic.main.item_weibo_intl_main.view.*

/**
 *
 * created by reol at 2019-09-04
 */
class WeiboIntlMainAdapter(private val weiboList: ArrayList<WeiboStatus.Statuse>) :
    RecyclerView.Adapter<WeiboIntlMainAdapter.WeiboIntlMainHolder>() {

    var cardClickEvent: ((View, Int) -> Unit)? = null
    private var contentClickEvent: ((View, Int) -> Unit)? = null
    private var commentClickEvent: ((View, Int) -> Unit)? = null
    private var forwardClickEvent: ((View, Int) -> Unit)? = null
    private var likeClickEvent: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeiboIntlMainHolder {
//        if (viewType == 0){
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weibo_intl_main, parent, false)
        return WeiboIntlMainHolder(view)
//        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = weiboList[position].getWeiboType()
        if (type == WeiboDataSource.WeiboContentType.WEIBO) {
            return 1
        } else {
            return 0
        }
    }

    override fun getItemCount(): Int {
        return weiboList.size
    }

    override fun onBindViewHolder(holder: WeiboIntlMainHolder, position: Int) {
        holder.bindView(weiboList[position])
        cardClickEvent?.let {
            holder.bindEvent(it, position)
        }
    }


    class WeiboIntlMainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(bean: WeiboStatus.Statuse) {
            itemView.item_weibo_intl_name.text = bean.user.screen_name
            itemView.item_weibo_intl_time.text = formTimeString(bean.created_at)
            itemView.item_weibo_intl_device.text = Html.fromHtml(bean.source)
            itemView.item_weibo_intl_text.text = bean.text

            Glide.with(itemView.context)
                .load(bean.user.avatar_large)
                .apply(RequestOptions().circleCrop())
                .into(itemView.item_weibo_intl_avatar)

            when (bean.getWeiboType()) {
                WeiboDataSource.WeiboContentType.TEXT -> {
                    itemView.item_weibo_intl_content.visibility = View.GONE
                    itemView.item_weibo_intl_pic_grid.visibility = View.GONE
                    itemView.item_weibo_intl_retweeted.visibility = View.GONE
                }
                WeiboDataSource.WeiboContentType.IMAGE -> {
                    itemView.item_weibo_intl_content.visibility = View.VISIBLE
                    itemView.item_weibo_intl_pic_grid.visibility = View.VISIBLE
                    itemView.item_weibo_intl_retweeted.visibility = View.GONE
                    val pics = ArrayList<String>()
                    bean.pic_urls.forEach {
                        pics.add(it.thumbnail_pic)
                    }
                    itemView.item_weibo_intl_pic_grid.apply {
                        val span = if (pics.size < 4) {
                            2
                        } else {
                            3
                        }
                        layoutManager =
                            GridLayoutManager(itemView.context, span, RecyclerView.VERTICAL, false)
                        adapter =
                            WeiboIntlPicsAdapter(pics, ScreenInfo.WIDTH - 2 * 10 - 12 * (span - 1))
                        addItemDecoration(DividerDecoration(6, 6, 6, 6))
                    }
                }
                WeiboDataSource.WeiboContentType.WEIBO -> {
                    itemView.item_weibo_intl_content.visibility = View.VISIBLE
                    itemView.item_weibo_intl_pic_grid.visibility = View.GONE
                    itemView.item_weibo_intl_retweeted.visibility = View.VISIBLE

                    val text =
                        Html.fromHtml("<a href=\"https://www.baidu.com\">@${bean.retweeted_status.user.screen_name}</a>:${bean.retweeted_status.text}")
                    itemView.item_weibo_intl_retweeted_text.text = text
                    itemView.item_weibo_intl_retweeted_info.text =
                        "Reposts ${bean.retweeted_status.reposts_count} Comments ${bean.retweeted_status.comments_count} Likes ${bean.retweeted_status.attitudes_count}"
                    //retweeted content 待补充
                }
            }

        }

        fun bindEvent(cardClick: ((View, Int) -> Unit), position: Int){
            itemView.item_weibo_intl_card.setOnClickListener {
                cardClick(it, position)
            }
        }
    }
}