package com.faker.netease.music.beans

/**
 *
 * copyright: ekwing.com
 * created by reol at 2019/4/22
 */
data class SongInfoBean(
    val name: String,
    val artists: ArrayList<String>,
    val url: String,
    val songId: String
)