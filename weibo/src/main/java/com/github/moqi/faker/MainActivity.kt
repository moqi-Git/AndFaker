package com.github.moqi.faker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.spRead
import com.github.moqi.faker.plugins.spWrite
import com.github.moqi.faker.plugins.toast
import com.github.moqi.faker.weibo.ui.WeiboIntlMainAct
import com.github.moqi.faker.weibo.datasource.WeiboDataSource
import com.sina.weibo.sdk.auth.Oauth2AccessToken
import com.sina.weibo.sdk.auth.WbAuthListener
import com.sina.weibo.sdk.auth.WbConnectErrorMessage
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var mAccessToken: Oauth2AccessToken? = null
    private lateinit var weibo: WeiboDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weibo = WeiboDataSource(this)

        tv_main.setOnClickListener {
            weibo.outh2(this, object : WbAuthListener {
                override fun onSuccess(p0: Oauth2AccessToken?) {
                    runOnUiThread {
                        mAccessToken = p0
                        mAccessToken?.let {
                            if (it.isSessionValid){
                                loge("token is ${it.token}")
                                spWrite("token", it.token)
                                loginSuccess(it.token)
                            }
                        }
                    }
                }

                override fun onFailure(p0: WbConnectErrorMessage?) {
                    toast("授权失败")
                }

                override fun cancel() {
                    toast("取消授权")
                }
            })
        }


        val savedToken = spRead("token")
        if(savedToken.isNotBlank()){
            loginSuccess(savedToken)
        }
    }

    private fun loginSuccess(token: String){
        val weiboMainPageIntent = Intent(this, WeiboIntlMainAct::class.java).apply {
            putExtra("token", token)
        }

        startActivity(weiboMainPageIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        weibo?.onActivityResult(requestCode, resultCode, data)
    }
}
