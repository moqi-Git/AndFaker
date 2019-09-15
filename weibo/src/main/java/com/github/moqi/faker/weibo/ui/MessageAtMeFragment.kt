package com.github.moqi.faker.weibo.ui

import android.os.Bundle
import android.view.View
import com.github.moqi.faker.R
import kotlinx.android.synthetic.main.fragment_weibo_message_atme.*

class MessageAtMeFragment : BaseFragment(){

    override fun getLayoutId(): Int = R.layout.fragment_weibo_message_atme


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tools_test_text.text = "s ${System.currentTimeMillis() / 1000 % 1000}"
    }
}