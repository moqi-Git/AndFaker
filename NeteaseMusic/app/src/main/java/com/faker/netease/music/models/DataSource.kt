package com.faker.netease.music.models

import android.database.Observable
import com.faker.netease.music.beans.SongInfoBean

/**
 *
 * copyright: moqi.kaze
 * created by reol at 2019/4/25
 */
interface DataSource {

    /**
     * 获取正在播放的歌曲列表
     */
    fun getPlayingList(): Observable<ArrayList<SongInfoBean>>
}