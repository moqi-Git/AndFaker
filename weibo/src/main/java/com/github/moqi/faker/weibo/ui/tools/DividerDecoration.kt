package com.github.moqi.faker.weibo.ui.tools

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 * created by reol at 2019-09-05
 */
class DividerDecoration(
    private val marginLeft: Int,
    private val marginTop: Int,
    private val marginRight: Int,
    private val marginBottom: Int
    ): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = marginTop
        outRect.left = marginLeft
        outRect.right = marginRight
        outRect.bottom = marginBottom
    }

}