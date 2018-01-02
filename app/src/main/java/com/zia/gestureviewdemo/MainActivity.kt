package com.zia.gestureviewdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gestureView.addOnLongPressListener {
            Toast.makeText(this@MainActivity,"long press",Toast.LENGTH_SHORT).show()
        }
        gestureView.setScaleSpeed(2)
        val adapter = MyAdapter(this)
        adapter.addScaleAble(object :MyAdapter.Scaleable{
            override fun scale(view: View) {
                gestureView.attachScaleView(view)
            }
        })
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
