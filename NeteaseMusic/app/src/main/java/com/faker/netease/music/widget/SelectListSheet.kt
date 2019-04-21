package com.faker.netease.music.widget

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.faker.netease.music.R
import com.faker.netease.music.adapter.SelectListAdapter
import com.faker.netease.music.beans.SongInfoBean
import kotlinx.android.synthetic.main.sheet_select_list.*
import kotlinx.android.synthetic.main.sheet_select_list.view.*

/**
 *
 * copyright: ekwing.com
 * created by reol at 2019/4/19
 */
class SelectListSheet: BottomSheetDialogFragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sheet_select_list, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {

        val fakeSongs = arrayListOf(
            SongInfoBean("赴鸿门", arrayListOf("排骨教主"), "http://dddddddd", "10298374"),
            SongInfoBean("赴鸿门", arrayListOf("排骨教主"), "http://dddddddd", "10298374"),
            SongInfoBean("赴鸿门", arrayListOf("排骨教主"), "http://dddddddd", "10298374"),
            SongInfoBean("赴鸿门", arrayListOf("排骨教主"), "http://dddddddd", "10298374")
        )

        val adapter = SelectListAdapter(fakeSongs)
        sheet_rv_content.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        sheet_rv_content.adapter = adapter
    }

}