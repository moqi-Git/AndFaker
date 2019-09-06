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

    private val APP_KEY = "4097729193"
    private val APP_SECRET = "13bd6463de985843ddca0fdef3f35ebb"
    private val REDIRECT_URL = "https://api.weibo.com/oauth2/default.html"
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
    }

    enum class WeiboContentType{
        TEXT, IMAGE, VIDEO, WEIBO
    }
}