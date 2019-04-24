package com.faker.netease.music.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * http拦截器：
 * 1. 统一设置默认请求参数
 * 2. 统一设置 header
 * 3. 支持动态修改设置
 * created by reol at 2019/4/25
 */
class ParamsInterceptor: Interceptor {

    private val ua = "pc"

    override fun intercept(chain: Interceptor.Chain): Response {

        val requset = chain.request()


        val builder = requset.newBuilder()


        updateHeader(builder)

        return chain.proceed(builder.build())
    }

    /**
     * 设置 UA，本项目需要模拟 PC 端获取 web 的数据
     */
    private fun updateHeader(builder: Request.Builder){
        builder.removeHeader("User-Agent")
        builder.addHeader("User-Agent", ua)
    }
}