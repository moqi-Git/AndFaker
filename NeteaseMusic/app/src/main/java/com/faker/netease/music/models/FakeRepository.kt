package com.faker.netease.music.models

import android.database.Observable
import com.faker.netease.music.beans.SongInfoBean

/**
 *
 * copyright: ekwing.com
 * created by reol at 2019/4/25
 */
class FakeRepository: DataSource {
    override fun getPlayingList(): Observable<ArrayList<SongInfoBean>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}