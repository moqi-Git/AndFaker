package com.github.moqi.faker.weibo.ui.tools

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ScreenHeightLayoutManager(context: Context, private val screenHeight: Int): LinearLayoutManager(context, RecyclerView.VERTICAL, false) {

    override fun onMeasure(
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
        widthSpec: Int,
        heightSpec: Int
    ) {
        super.onMeasure(recycler, state, widthSpec, heightSpec)
        setMeasuredDimension(width, screenHeight)
    }
}