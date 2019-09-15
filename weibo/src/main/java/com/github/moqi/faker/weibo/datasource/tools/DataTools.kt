package com.github.moqi.faker.weibo.datasource.tools

import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.weibo.beans.WeiboStatus
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import java.text.ParseException
import java.text.SimpleDateFormat

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

fun formTimeString(createAt: String): String{
    val format = SimpleDateFormat("EEE MMM dd hh:mm:ss Z yyyy")

    try {
        val date = format.parse(createAt)
//        loge(date.time.toString())

        val dMin = (System.currentTimeMillis() - date.time) /1000 /60
        if (dMin < 1440){
            // 一天之内的显示 ** ago
            if (dMin < 60){
                return "$dMin min ago"
            } else {
                return "${dMin/60} h ${dMin%60} m ago"
            }
        } else {
            // 超过一天的显示具体 月-日 时:分
            val newFormat = SimpleDateFormat("MM-dd hh:mm")
            return newFormat.format(date)
        }

    }catch (e: ParseException){
        e.printStackTrace()
    }

    return "error"
}