package com.example.hpsus.kotlintest.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.view.LayoutInflater
import com.example.hpsus.kotlintest.InfoActivity
import com.example.hpsus.kotlintest.MainActivity
import com.example.hpsus.kotlintest.R
import com.example.hpsus.kotlintest.model.mApartment
import com.example.hpsus.kotlintest.model.mHome
import com.example.hpsus.kotlintest.sqlite.*
import kotlinx.android.synthetic.main.item_apartment.view.*
import kotlinx.android.synthetic.main.item_home.view.*

class ApartmentAdapter(internal var activity: Context, var apartmentList:ArrayList<mApartment>) : BaseAdapter() {

    internal var inflater: LayoutInflater
    init{
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        view=inflater.inflate(R.layout.item_apartment,null)
        view.tvAindiApartmentText.text=apartmentList[position].indiApartment.toString()
        view.tvAindiHomeText.text=apartmentList[position].indiHome.toString()
        view.tvAfloorText.text=apartmentList[position].floor.toString()
        view.tvAareaText.text= String.format("%.1f",apartmentList[position].area)
        return view
    }

    override fun getItem(position: Int): Any {
        return apartmentList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return apartmentList.size
    }
}