package com.github.moqi.faker.weibo.ui

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.github.moqi.faker.R
import com.github.moqi.faker.plugins.*

import kotlinx.android.synthetic.main.activity_weibo_intl_main.*

class WeiboIntlMainAct : AppCompatActivity() {

    private lateinit var fragTimeline: TimelineFragment
    private lateinit var fragMessage: MessageFragment

    private lateinit var toggleNavi: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weibo_intl_main)

        setSupportActionBar(weibo_toolbar)
        setupTitleBar()

        initView()
    }

    private fun initView() {
        fragTimeline = TimelineFragment() // 数据独立后，创建方法不需要传入数据，就不需要静态方法了
        fragMessage = MessageFragment()

        val trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.weibo_main_container, fragMessage, "message")
        trans.hide(fragMessage)
        trans.add(R.id.weibo_main_container, fragTimeline, "timeline")
        trans.commit()

        weibo_main_navi_bottom.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_weibo_timeline -> {
                    hideChangeFragment(fragMessage, fragTimeline)
                    true
                }
                R.id.menu_weibo_message -> {
                    hideChangeFragment(fragTimeline, fragMessage)
                    true
                }
                else -> false
            }
        }

        weibo_main_navi_bottom.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.menu_weibo_timeline -> {
                    toast("未实现功能：回到顶部，刷新时间线")
                }
            }
        }
    }

    private fun setupTitleBar(){
        toggleNavi = ActionBarDrawerToggle(this, weibo_main_drawer, weibo_toolbar, R.string.drawer_open, R.string.drawer_close)
        weibo_main_drawer.addDrawerListener(toggleNavi)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggleNavi?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggleNavi?.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggleNavi?.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
