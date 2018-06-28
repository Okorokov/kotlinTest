package com.example.hpsus.kotlintest

import android.content.Context

class Preference(context: Context) {
    val PRE_NAME="SPE"
    val PRE_VERS="VERS"
    val preference=context.getSharedPreferences(PRE_NAME,Context.MODE_PRIVATE)

    fun getVers(): Int{
        return preference.getInt(PRE_VERS,-1)
    }
    fun setVers(vers: Int){
        val editor = preference.edit()
        editor.putInt(PRE_VERS,vers)
        editor.apply()
    }
}