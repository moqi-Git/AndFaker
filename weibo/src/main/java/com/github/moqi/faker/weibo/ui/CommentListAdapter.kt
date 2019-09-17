package com.github.moqi.faker.weibo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.weibo.beans.WeiboCommentBean
import com.github.moqi.faker.weibo.datasource.tools.formTimeString
import kotlinx.android.synthetic.main.item_weibo_comment.view.*

/**
 *
 * created by reol at 2019-09-18
 */
class CommentListAdapter(private val mCommentList: ArrayList<WeiboCommentBean.CommentContent>): RecyclerView.Adapter<CommentListAdapter.CommentListHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentListHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weibo_comment, parent, false)
        return CommentListHolder(view)
    }

    override fun getItemCount(): Int = mCommentList.size

    override fun onBindViewHolder(holder: CommentListHolder, position: Int) {
        holder.bindView(mCommentList[position])
    }


    class CommentListHolder(itemview: View): RecyclerView.ViewHolder(itemview){

        fun bindView(bean: WeiboCommentBean.CommentContent){
            itemView.item_weibo_comments_name.text = bean.user.screen_name
            itemView.item_weibo_comments_time.text = formTimeString(bean.created_at)
            itemView.item_weibo_comments_text.text = bean.text

            Glide.with(itemView.context)
                .load(bean.user.avatar_large)
                .apply(RequestOptions().circleCrop())
                .into(itemView.item_weibo_comments_avatar)
        }
    }
}