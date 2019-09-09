package com.github.moqi.faker.weibo.beans

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class WeiboCacheBean {

    @Entity(tableName = "weibo")
    data class Statuse(

        @PrimaryKey
        val id: Long,
        val created_at: String,
        @ColumnInfo(name="blog_type")
        val mblogtype: Int,
        val more_info_type: Int,
        val text: String,
        val user_name: String,
        val user_avatar: String
    )

}