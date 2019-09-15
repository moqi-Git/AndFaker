package com.github.moqi.faker.weibo.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.setupWithFragments
import kotlinx.android.synthetic.main.fragment_weibo_message.*

class MessageFragment: BaseFragment() {

    private val mFragments = ArrayList<Fragment>()

    override fun getLayoutId(): Int = R.layout.fragment_weibo_message

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        mFragments.add(MessageAtMeFragment())
        mFragments.add(MessageAtMeFragment())
        mFragments.add(MessageAtMeFragment())
        val adapter = object : FragmentPagerAdapter(activity!!.supportFragmentManager) {
            override fun getItem(position: Int): Fragment = mFragments[position]
            override fun getCount(): Int = mFragments.size
            override fun getPageTitle(position: Int): CharSequence? = arrayListOf("@ME", "COMMENT", "CHAT")[position]
        }
        weibo_message_viewpager.adapter = adapter

        weibo_message_tabs.setupWithViewPager(weibo_message_viewpager)
    }
}