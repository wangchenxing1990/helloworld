package com.technology.waangyukui.kotlinStudy

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.technology.waangyukui.mycyclerapp.R

/**
 * Created by lenvo on 2018/5/23.
 */
class MainTwoActivity : AppCompatActivity(), View.OnClickListener {

    private var text: TextView? = null
    private var buttonQuery: Button? = null
    private var recyclerView: RecyclerView? = null
    private var datas: MutableList<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        getData()
        text = findViewById<TextView>(R.id.text_view)
        buttonQuery = findViewById<Button>(R.id.button_query)
        recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        text!!.setText(R.string.study_kotlin)
        text!!.textSize = 20f
        text!!.setTextColor(Color.parseColor("#ff0000"))
        buttonQuery!!.setOnClickListener(this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false))
        recyclerView!!.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        var adapter:MyAdapter = MyAdapter(datas)
        recyclerView!!.adapter = adapter

    }

    fun getData(){
        datas=ArrayList<String>()
         for( i in 1..20){
             datas!!.add(i.toString())
        }
    }
    override fun onClick(view: View) {
        var id = view.id
        when (id) {
            R.id.button_query -> {
                startOtherActivity()
            }
        }
    }

    fun startOtherActivity() {
        var intent = Intent()
        intent.setClass(applicationContext, SeekTicketActivity::class.java)
        startActivity(intent)
      //  Toast.makeText(applicationContext, "点击开启另外一个界面", Toast.LENGTH_SHORT).show()
    }
}