package com.github.moqi.faker.weibo.datasource

import androidx.room.Dao
import androidx.room.Query
import com.github.moqi.faker.weibo.beans.WeiboCacheBean

@Dao
interface WeiboCacheDao {

    @Query("SELECT * FROM weibo")
    fun getDefaultLastest(): List<WeiboCacheBean.Statuse>

    //todo: 建表，做数据缓存
}