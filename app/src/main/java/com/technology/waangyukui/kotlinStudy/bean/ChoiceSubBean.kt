package com.technology.waangyukui.kotlinStudy.bean

/**
 * Created by lenvo on 2018/6/14.
 */

data class ChoiceSubBean(
    val data: List<DataSub>,
    val code: Int,
    val hasnext: String
)

data class DataSub(
    val shopName: String,
    val image: String,
    val taobaoId: String,
    val couponPromotUrl: String,
    val sellCount: String,
    val goodsName: String,
    val endTime: String,
    val cateId: String,
    val price: String,
    val couponDenomination: Int
)