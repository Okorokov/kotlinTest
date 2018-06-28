package com.example.hpsus.kotlintest.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.system.Os.remove
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_info.view.*
import kotlinx.android.synthetic.main.item_apartment.view.*
import kotlinx.android.synthetic.main.item_home.view.*

class ApartmentAdapter(internal var activity: Context, var apartmentList:ArrayList<mApartment>) : BaseAdapter() {
    val Tag: String = "ApartmentAdapter"
    var dbHelper: DatabaseHelper = DatabaseHelper(activity)
    internal var inflater: LayoutInflater
    internal var inflaterF: LayoutInflater
    init{
        inflater=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflaterF=activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewF: View
        view=inflater.inflate(R.layout.item_apartment,null)
        viewF=inflater.inflate(R.layout.activity_info,null)
        if(apartmentList[position].visible==0) {
            view.tvAindiApartmentText.text = apartmentList[position].indiApartment.toString()
            view.tvAindiHomeText.text = apartmentList[position].indiHome.toString()
            view.tvAfloorText.text = apartmentList[position].floor.toString()
            view.tvAareaText.text = String.format("%.1f", apartmentList[position].area)
        }
        view.setOnLongClickListener {
            val li = LayoutInflater.from(activity)
            val promptsView = li.inflate(R.layout.activity_info, null)
            val mDialogBuilder = AlertDialog.Builder(activity)
            mDialogBuilder.setView(promptsView)
            mDialogBuilder.setCancelable(false)
            mDialogBuilder.setTitle("Удалить")
            mDialogBuilder.setPositiveButton("OK"){dialog,which ->
                dbHelper.writeApartment(apartmentList[position].indiApartment.toString())
                apartmentList[position].visible=1
                apartmentList.removeAt(position)

                notifyDataSetChanged();
                dialog.cancel()
            }
            mDialogBuilder.setNegativeButton("No"){dialog,which ->
                dialog.cancel()
            }
            val dialog: AlertDialog = mDialogBuilder.create()
            dialog.show()


            true
        }
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