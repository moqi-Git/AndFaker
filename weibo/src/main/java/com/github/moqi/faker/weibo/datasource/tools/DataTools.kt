package com.github.moqi.faker.weibo.datasource.tools

import com.github.moqi.faker.weibo.beans.WeiboStatus
import com.github.moqi.faker.weibo.datasource.WeiboDataSource

/**
 *
 * created by reol at 2019-09-05
 */


fun WeiboStatus.Statuse.getWeiboType(): WeiboDataSource.WeiboContentType{

    return when{
        retweeted_status != null -> WeiboDataSource.WeiboContentType.WEIBO
        pic_urls?.size?:0 != 0 -> WeiboDataSource.WeiboContentType.IMAGE

        else -> WeiboDataSource.WeiboContentType.TEXT
    }
}