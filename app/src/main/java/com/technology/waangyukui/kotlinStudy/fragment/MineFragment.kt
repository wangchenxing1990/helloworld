package com.technology.waangyukui.kotlinStudy.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.technology.waangyukui.mycyclerapp.R

/**
 * Created by lenvo on 2018/6/13.
 */
class MineFragment :Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_mine,container,false)
        return view
    }
}