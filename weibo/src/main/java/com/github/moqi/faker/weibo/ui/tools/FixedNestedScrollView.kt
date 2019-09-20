package com.github.moqi.faker.weibo.ui.tools

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.widget.NestedScrollView
import com.github.moqi.faker.plugins.loge
import com.github.moqi.faker.plugins.loges

class FixedNestedScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    var interceptScroll = true
    var parentScrollDistance = 0

//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        if (interceptScroll){
//            return true
//        }
//        return super.onInterceptTouchEvent(ev)
//    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        loges("onStart=${super.onStartNestedScroll(child, target, axes, type)}")
        return super.onStartNestedScroll(child, target, axes, type)
    }

    override fun onNestedScrollAccepted(child: View, target: View, nestedScrollAxes: Int) {
        loges("onNestedScrollAccepted")
        super.onNestedScrollAccepted(child, target, nestedScrollAxes)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        loges("ndy=$dy, ndx=$dx")
        if (dy > 0 && scrollY < parentScrollDistance) {
            scrollBy(0, dy)
            consumed[1] = dy
        } else {
            super.onNestedPreScroll(target, dx, dy, consumed, type)
        }
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        loges("dyc=$dyConsumed, dxc=$dxConsumed")
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        loges("tdyc=$dyConsumed, tdxc=$dxConsumed")
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }


    override fun onNestedFling(
        target: View,
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        loges("fvy=$velocityY, fvx=$velocityX")
        return super.onNestedFling(target, velocityX, velocityY, consumed)
    }

    override fun onNestedPreFling(target: View, velocityX: Float, velocityY: Float): Boolean {
        loges("pfvy=$velocityY, pfvx=$velocityX")
        return super.onNestedPreFling(target, velocityX, velocityY)
    }


}