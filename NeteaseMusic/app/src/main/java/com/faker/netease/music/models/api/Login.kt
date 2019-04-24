package com.faker.netease.music.models.api

import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * copyright: ekwing.com
 * created by reol at 2019/4/25
 */

interface Login{

    @FormUrlEncoded
    @POST("login")
    fun loginByEmail(@Field("username")email: String, @Field("password") passwd: String,
                     @Field("rememberLogin") remember: Boolean = true)
}