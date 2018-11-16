package com.technology.waangyukui.kotlinStudy.bean

/**
 * Created by lenvo on 2018/6/13.
 */

data class ChoiceBean(
    val code: Int,
    val data: List<Data>
)

data class Data(
    val cateId: String,
    val name: String
)