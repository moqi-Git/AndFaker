package com.github.moqi.faker.plugins

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import java.lang.Exception
import java.lang.IllegalStateException

/**
 *
 * created by reol at 2019-09-03
 */

fun Context.toast(msg: String){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun loge(text: String){
    Log.e("asdfg", text)
}

fun loges(text: String, specialTag: String = "=OxO="){
    Log.e(specialTag, text)
}

fun Context.spWrite(key: String, value: String, file: String = "MAKI_KEY"){
    val sp = getSharedPreferences(file, 0)
    sp.edit().putString(key, value).apply()
}

fun Context.spRead(key: String, file: String = "MAKI_KEY"): String{
    val sp = getSharedPreferences(file, 0)
    return sp.getString(key, "")
}

fun xlog(text: String){
    val maxLength = 2000

    var start = 0
    var length = text.length

    while (true){
        if (length > maxLength){
            loge(text.substring(start, start + maxLength))
            start += maxLength
            length -= maxLength
        } else {
            loge(text.substring(start, start + length))
            break
        }
    }
}

fun Context.pxcdp(dpValue: Float): Int{
    val scale = applicationContext.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.dpcpx(pxValue: Int): Float{
    val scale = applicationContext.resources.displayMetrics.density
    return pxValue.toFloat() / scale + 0.5f
}

fun ViewPager.setupWithFragments(fragmentManager: FragmentManager, fragments: ArrayList<Fragment>){
    val adapter = object : FragmentPagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment = fragments[position]
        override fun getCount(): Int = fragments.size
    }
    this.adapter = adapter
}

fun FragmentActivity.replaceFragment(containerId: Int, fragment: Fragment){
    val trans = supportFragmentManager.beginTransaction()
    trans.replace(containerId, fragment)
    trans.commit()
}

fun FragmentActivity.hideChangeFragment(hideOne: Fragment, showOne: Fragment){
    val trans = supportFragmentManager.beginTransaction()
    trans.hide(hideOne)
    if (showOne.isAdded){
        trans.show(showOne)
    } else {
        throw IllegalStateException("You must add the showOne before change to it")
    }

    trans.commit()
}