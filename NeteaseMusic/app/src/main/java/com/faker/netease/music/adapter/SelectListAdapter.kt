package com.faker.netease.music.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.faker.netease.music.R
import com.faker.netease.music.beans.SongInfoBean
import kotlinx.android.synthetic.main.item_sheet_songs.view.*

/**
 *
 * copyright: ekwing.com
 * created by reol at 2019/4/22
 */
class SelectListAdapter(private val mList: ArrayList<SongInfoBean>): RecyclerView.Adapter<SelectListAdapter.SelectListVH>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SelectListVH {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_sheet_songs, p0, false)
        return SelectListVH(view)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(p0: SelectListVH, p1: Int) {
        p0.bindView(mList[p1])
    }


    class SelectListVH(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bindView(bean: SongInfoBean){
            itemView.item_song_tv_name.text = bean.name
            itemView.item_song_tv_artist.text = bean.artists[0]
        }
    }
}