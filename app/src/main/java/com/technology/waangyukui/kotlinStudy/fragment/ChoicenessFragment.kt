package com.technology.waangyukui.kotlinStudy.fragment

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.technology.waangyukui.kotlinStudy.bean.ChoiceBean
import com.technology.waangyukui.kotlinStudy.bean.Data
import com.technology.waangyukui.mycyclerapp.MyApplication.context
import com.technology.waangyukui.mycyclerapp.R
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.Callback
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator.MODE_WRAP_CONTENT
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

/**
 * Created by lenvo on 2018/6/12.
 */
class ChoicenessFragment : Fragment() {
    val HOST_URL: String = "https://www.xunquan.shop/api/v2.0/xunquan/cate?page=1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var magic_indicator:MagicIndicator?=null
    var view_pager:ViewPager?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.choiceness_fragment, container, false)
         magic_indicator = view.findViewById<MagicIndicator>(R.id.magic_indicator)
         view_pager = view.findViewById<ViewPager>(R.id.view_pager)
         getData()
        return view
    }

    private fun initMagicIndecator() {
        var commonNavifator=CommonNavigator(activity)
        commonNavifator.isSkimOver=true
        commonNavifator.adapter=MyCommonNavigatorAdapter(choiceBean!!)
        magic_indicator!!.navigator=commonNavifator
        ViewPagerHelper.bind(magic_indicator,view_pager)
    }

    class MyCommonNavigatorAdapter(choiceBean: ChoiceBean) : CommonNavigatorAdapter(){
        var choiceBeans:ChoiceBean=choiceBean
        override fun getTitleView(p0: Context, p1: Int): IPagerTitleView? {
            var simplePagerTitleView= ColorTransitionPagerTitleView(context)
            simplePagerTitleView.normalColor=Color.GRAY
            simplePagerTitleView.selectedColor= 0xfffc4b36.toInt()
            simplePagerTitleView.text=choiceBeans.data[p1].name
            return simplePagerTitleView
        }

        override fun getCount(): Int {
         return choiceBeans.data.size
        }

        override fun getIndicator(p0: Context): IPagerIndicator {
        var linePagerIndicator=LinePagerIndicator(context)
            linePagerIndicator.mode=MODE_WRAP_CONTENT
            linePagerIndicator.setColors(0xfffc4b36.toInt())
            return linePagerIndicator
        }
    }

    class MyViewPagerAdapter(listFragments: MutableList<Fragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
        var listFragment:MutableList <Fragment> = listFragments
        override fun getItem(position: Int): Fragment? {
         return listFragment[position]
        }

        override fun getCount(): Int {
            return listFragment.size
        }

    }
    var listFragment:MutableList<Fragment> =ArrayList<Fragment>()
    var mHandler=object: Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                100->{
                    choiceBean= msg.obj as ChoiceBean
                    for (i in choiceBean!!.data){
                        var fragment=ChoiceSubFragment(i.cateId)
                        listFragment.add(fragment)
                    }
                   var adapter= MyViewPagerAdapter(listFragment!!, fragmentManager!!)
                    initMagicIndecator()
                    view_pager!!.adapter=adapter
                }
            }
        }
    }
    var choiceBean: ChoiceBean? = null
    fun getData() {
        OkHttpUtils.get().url(HOST_URL).build().execute(object : Callback<ChoiceBean>() {
            override fun onError(call: Call?, e: Exception?, id: Int) {
            }

            override fun parseNetworkResponse(response: Response, id: Int): ChoiceBean {
                var str = response.body()!!.string()
                var gson = Gson()
               var choiceBean = gson.fromJson(str, ChoiceBean::class.java)
                return choiceBean as ChoiceBean
            }

            override fun onResponse(response: ChoiceBean, id: Int) {
                var message=Message()
                message.what=100
                message.obj=response
                mHandler.sendMessage(message)
            }
        })
    }

    fun getTwoData() {
        var request = Request.Builder().url(HOST_URL).get().build()
        var okhttp = OkHttpClient()
        val call = okhttp.newCall(request)
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                var string = response.body()!!.string()
                Log.i("寻券获取的精选界面的数据", string)
            }

        })
    }
}