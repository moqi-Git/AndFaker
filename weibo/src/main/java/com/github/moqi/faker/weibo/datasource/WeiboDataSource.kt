package com.github.moqi.faker.weibo.datasource

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.sina.weibo.sdk.WbSdk
import com.sina.weibo.sdk.auth.AuthInfo
import com.sina.weibo.sdk.auth.WbAuthListener
import com.sina.weibo.sdk.auth.sso.SsoHandler

/**
 *
 * created by reol at 2019-09-02
 */
class WeiboDataSource(private val context: Context) {

    private val APP_KEY = "4015293047"
    private val APP_SECRET = "6f6fc4049f5180c56a3bc6b7380dbfdc"
    private val REDIRECT_URL = "https://github.com/moqi-Git"
    private val SCOPE = ("email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write")

    private lateinit var mSsoHandler: SsoHandler

    init {
        val mAuthInfo = AuthInfo(context, APP_KEY, REDIRECT_URL, SCOPE)
        WbSdk.install(context, mAuthInfo)
    }

    fun outh2(activity: Activity, wbAuthListener: WbAuthListener){
        mSsoHandler = SsoHandler(activity)
        mSsoHandler.authorize(wbAuthListener)
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        if (mSsoHandler != null){
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data)
        }
    }

    companion object{
        @JvmStatic val URL_TIMELINE = "https://api.weibo.com/2/statuses/home_timeline.json"
        @JvmStatic val URL_GET_COMMENTS = "https://api.weibo.com/2/comments/show.json"
        @JvmStatic val URL_USER_TIMELINE = "https://api.weibo.com/2/statuses/user_timeline.json"
    }

    enum class WeiboContentType{
        TEXT, IMAGE, LINK, WEIBO
    }
}