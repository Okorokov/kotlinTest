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
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(internal var activity: Context,var homeList:ArrayList<mHome>) : BaseAdapter() {

    internal var inflater: LayoutInflater
    init{
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        view=inflater.inflate(R.layout.item_home,null)
        view.tvHome.text=homeList[position].nameHome.toString()

        view.setOnClickListener {
            val intent=  Intent(activity, InfoActivity::class.java)
            intent.putExtra(HOME_COL_INDIHOME,homeList[position].indiHome);
            intent.putExtra(HOME_COL_NAMEHOME,homeList[position].nameHome.toString());
            intent.putExtra(HOME_COL_FLOORS,homeList[position].floors);
            intent.putExtra(HOME_COL_DEVELOPERNAME,homeList[position].developerName.toString());
            activity.startActivity(intent)
        }

        return view
    }

    override fun getItem(position: Int): Any {
        return homeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return homeList.size
    }
}