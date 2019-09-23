package com.github.moqi.faker.weibo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.NetRequest
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.spRead
import com.github.moqi.faker.plugins.xlog
import com.github.moqi.faker.weibo.datasource.WeiboDataSource

class WeiboUserActivity : AppCompatActivity() {

    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_user)

        initView()
        loadUserWeibo(page)
    }

    private fun loadUserWeibo(page: Int){
        val token = spRead("token")
        val uid = intent.getLongExtra("uid", 0L)
        if (uid == 0L){
            loge("ERROR : uid not found")
        }

        NetRequest().get(WeiboDataSource.URL_USER_TIMELINE + "?access_token=$token&page=$page&uid=$uid"){isSucc, result ->
            xlog(result)

            if(isSucc){

            }
        }
    }

    private fun initView(){

    }
}
