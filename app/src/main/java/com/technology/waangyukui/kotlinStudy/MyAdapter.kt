package com.technology.waangyukui.kotlinStudy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.technology.waangyukui.mycyclerapp.R

/**
 * Created by lenvo on 2018/6/12.
 */
class MyAdapter(var datas: List<String>?) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
      holder!!.textView.setText(datas!!.get(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_item, parent, false)
        var holder = MyViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return datas!!.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView:TextView
        init {
            textView= itemView.findViewById(R.id.text_view_item)
        }
    }
}