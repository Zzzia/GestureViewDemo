package com.zia.gestureviewdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.header.view.*

/**
 * Created by zia on 2017/11/22.
 */
class MyAdapter(private val context: Context) : RecyclerView.Adapter<MyAdapter.MyHolder>() {

    interface Scaleable{
        fun scale(view: View)
    }

    private var scaleable : Scaleable? = null

    fun addScaleAble(scaleable: Scaleable){
        this.scaleable = scaleable
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder {
        if (viewType == 0) {
            return MyHeaderHolder(LayoutInflater.from(context).inflate(R.layout.header, parent, false))
        }
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MyHolder(view)
    }

    override fun onBindViewHolder(holder: MyHolder?, position: Int) {
        if (position == 0 && scaleable != null){
            scaleable?.scale((holder as MyHeaderHolder).image1)
            scaleable?.scale((holder as MyHeaderHolder).image)
        }
        holder?.itemView?.setOnClickListener {
            Toast.makeText(context, "click ${position + 1} item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return 20
    }

    open inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class MyHeaderHolder(itemView: View) : MyHolder(itemView){
        val image : ImageView = itemView.findViewById(R.id.header_imageView)
        val image1 : ImageView = itemView.findViewById(R.id.header_imageView1)
    }
}