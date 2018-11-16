package com.technology.waangyukui.kotlinStudy.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.technology.waangyukui.kotlinStudy.bean.DataSub
import com.technology.waangyukui.mycyclerapp.MyApplication.context
import com.technology.waangyukui.mycyclerapp.R

/**
 * Created by lenvo on 2018/6/14.
 */
class MyRecyclerAdapter(context: Context, siuList: List<DataSub>) : RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder>() {
    var siuListData: List<DataSub>? = siuList
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        Picasso.with(context).load(siuListData!![position].image).into(holder!!.imageView)
        holder!!.coupontitle.text = siuListData!![position].goodsName
        holder!!.couponprice.text = siuListData!![position].price
        holder!!.coupondenomation.text = siuListData!![position].couponDenomination.toString()
        holder!!.couponshopname.text = siuListData!![position].shopName
        holder!!.couponcout.text = siuListData!![position].sellCount
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var vh = MyViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_sub_item, parent, false))
        return vh
    }

    override fun getItemCount(): Int {
        return siuListData!!.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView = view.findViewById<ImageView>(R.id.couponimage)
        var coupontitle = view.findViewById<TextView>(R.id.coupontitle)
        var couponcout = view.findViewById<TextView>(R.id.couponcout)
        var couponprice = view.findViewById<TextView>(R.id.couponprice)
        var coupondenomation = view.findViewById<TextView>(R.id.coupondenomation)
        var couponshopname = view.findViewById<TextView>(R.id.couponshopname)
    }

}