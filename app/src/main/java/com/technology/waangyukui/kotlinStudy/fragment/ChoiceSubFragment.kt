package com.technology.waangyukui.kotlinStudy.fragment

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.technology.waangyukui.database.MyDataBaseHelper
import com.technology.waangyukui.kotlinStudy.adapter.MyRecyclerAdapter
import com.technology.waangyukui.kotlinStudy.bean.ChoiceSubBean
import com.technology.waangyukui.mycyclerapp.R
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import okhttp3.Call
import okhttp3.Response
import java.lang.Exception

@SuppressLint("ValidFragment")
/**
 * Created by lenvo on 2018/6/13.
 */
class ChoiceSubFragment(cateId: String) : Fragment() {
    val HOST_URL: String = "https://www.xunquan.shop/api/v2.0/xunquan/cat/"
    var cateId: String? = cateId
    var myDataBaseHelper:MyDataBaseHelper?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var recyclerView:RecyclerView?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_sub_choices, container, false)
        recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView!!.layoutManager= LinearLayoutManager(context)
        myDataBaseHelper= MyDataBaseHelper(context)
        getData()
        return view
    }

    fun getData() {
        OkHttpUtils.get().url(HOST_URL + cateId + "?page=1").build().execute(object : Callback<ChoiceSubBean>() {
            override fun onError(call: Call?, e: Exception?, id: Int) {
            }

            override fun parseNetworkResponse(response: Response, id: Int): ChoiceSubBean {
                var str = response.body()!!.string()
                Log.i("数据的获取的精选界面的子界面", str)
                var gson = Gson()
                var choiceBean = gson.fromJson(str, ChoiceSubBean::class.java)
                return choiceBean
            }

            override fun onResponse(response: ChoiceSubBean, id: Int) {
                var message = Message()
                message.what = 100
                message.obj = response
                mHandler.sendMessage(message)
            }
        })
    }

    var  mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            var time=msg.what
            when(time){
                100->{
                   var message:ChoiceSubBean= msg.obj as ChoiceSubBean

                    for(i in message.data){
                        var contextValues=ContentValues()
                        contextValues!!.put(i.shopName,i.shopName)
                        contextValues!!.put(i.image,i.image)
                        contextValues!!.put(i.taobaoId,i.taobaoId)
                        contextValues!!.put(i.couponPromotUrl,i.couponPromotUrl)
                        contextValues!!.put(i.sellCount,i.sellCount)
                        contextValues!!.put(i.goodsName,i.goodsName)
                        contextValues!!.put(i.endTime,i.endTime)
                        contextValues!!.put(i.cateId,i.cateId)
                        contextValues!!.put(i.price,i.price)
                        contextValues!!.put(i.couponDenomination.toString(),i.couponDenomination.toString())

                        myDataBaseHelper!!.insertGoods(contextValues)

                    }
                    recyclerView!!.adapter = MyRecyclerAdapter(context!!, message.data)
                }
            }
        }
    }

}