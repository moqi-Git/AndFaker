package com.github.moqi.faker.weibo.ui

import android.graphics.Color
import android.media.Image
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.loge

/**
 *
 * created by reol at 2019-09-06
 */
class WeiboIntlPicsAdapter(private val picList: ArrayList<String>, private val totalWidth: Int): RecyclerView.Adapter<WeiboIntlPicsAdapter.WeiboIntlPicsHolder>() {

    var clickEvent: ((View, Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeiboIntlPicsHolder {
        // 动态创建 ImageView 比较好吧

        val span = getSpan(picList.size)
        val imWidth = totalWidth / span
        val imageView = ImageView(parent.context)

        val params = imageView.layoutParams?.apply {
            width = imWidth
            height = imWidth
        } ?:LinearLayout.LayoutParams(imWidth, imWidth)
        imageView.layoutParams = params
        imageView.setBackgroundColor(Color.GREEN)

        return WeiboIntlPicsHolder(imageView)
    }

    override fun getItemCount(): Int = picList.size

    override fun onBindViewHolder(holder: WeiboIntlPicsHolder, position: Int) {
        holder.bindView(picList[position])
    }

    companion object{
        @JvmStatic
        fun getSpan(picNum: Int): Int{
            return when{
                picNum < 2 -> 1
                picNum in 2..4 -> 2
                picNum in 5..9 -> 3
                else -> 3
            }
        }
    }


    class WeiboIntlPicsHolder(itemView: ImageView): RecyclerView.ViewHolder(itemView){
        fun bindView(url: String){ // todo：区分 url 公共部分和图片 id
            if (url.startsWith("http://")){
                url.replace("http://", "https://")
            }
            loge("Glide load url $url")
            Glide.with(itemView.context)
                .load(url)
                .apply(RequestOptions().placeholder(R.drawable.ic_share).error(R.drawable.ic_like).centerCrop())
                .into(itemView as ImageView)
        }

        fun bindEvent(event: ((View, Int) -> Unit), position: Int){
            itemView.setOnClickListener{
                event(it, position)
            }
        }
    }
}