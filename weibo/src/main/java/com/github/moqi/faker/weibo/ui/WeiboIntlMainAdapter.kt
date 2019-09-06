package com.github.moqi.faker.weibo.ui

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.weibo.beans.WeiboStatus
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.github.moqi.faker.weibo.datasource.tools.getWeiboType
import kotlinx.android.synthetic.main.item_weibo_intl_main.view.*

/**
 *
 * created by reol at 2019-09-04
 */
class WeiboIntlMainAdapter(private val weiboList: ArrayList<WeiboStatus.Statuse>): RecyclerView.Adapter<WeiboIntlMainAdapter.WeiboIntlMainHolder>() {

    private var cardClickEvent: ((View, Int) -> Unit)? = null
    private var contentClickEvent: ((View, Int) -> Unit)? = null
    private var commentClickEvent: ((View, Int) -> Unit)? = null
    private var forwardClickEvent: ((View, Int) -> Unit)? = null
    private var likeClickEvent: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeiboIntlMainHolder {
//        if (viewType == 0){
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weibo_intl_main, parent, false)
            return WeiboIntlMainHolder(view)
//        }
    }

    override fun getItemViewType(position: Int): Int {
        val type = weiboList[position].getWeiboType()
        if (type == WeiboDataSource.WeiboContentType.WEIBO){
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
    }


    class WeiboIntlMainHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bindView(bean: WeiboStatus.Statuse){
            itemView.item_weibo_intl_name.text = bean.user.screen_name
            itemView.item_weibo_intl_time.text = bean.created_at
            itemView.item_weibo_intl_device.text = Html.fromHtml(bean.source)
            itemView.item_weibo_intl_text.text = bean.text

            Glide.with(itemView.context)
                .load(bean.user.avatar_large)
                .apply(RequestOptions().circleCrop())
                .into(itemView.item_weibo_intl_avatar)

            when(bean.getWeiboType()){
                WeiboDataSource.WeiboContentType.TEXT -> {
                    itemView.item_weibo_intl_content.visibility = View.GONE
                }
                WeiboDataSource.WeiboContentType.IMAGE -> {
                    itemView.test_tv_content_type.text = bean.pic_urls.toString()
                }
                WeiboDataSource.WeiboContentType.WEIBO -> {
                    itemView.test_tv_content_type.text = bean.retweeted_status.text
                }
            }

        }
    }
}