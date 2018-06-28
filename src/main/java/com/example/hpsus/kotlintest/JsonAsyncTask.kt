package com.example.hpsus.kotlintest

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.os.AsyncTask
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import com.example.hpsus.kotlintest.adapter.HomeAdapter
import com.example.hpsus.kotlintest.datajson.Apartment
import com.example.hpsus.kotlintest.datajson.Data
import com.example.hpsus.kotlintest.datajson.Home
import com.example.hpsus.kotlintest.model.mApartment
import com.example.hpsus.kotlintest.model.mHome
import com.example.hpsus.kotlintest.sqlite.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.nio.charset.Charset


class JsonAsyncTask(val stream:InputStream,val context: Context, val lvHome: ListView): AsyncTask<Void, Void, Void>() {
    val Tag: String = "JsonAsyncTask"
    var mHomes= ArrayList<Home>()
    var mApartments= ArrayList<Apartment>()
    var dbHomes= ArrayList<mHome>()
    var dbApartments= ArrayList<mApartment>()
    lateinit var mData: Data
    var dbHelper: DatabaseHelper = DatabaseHelper(context)
    var database: SQLiteDatabase = dbHelper.writableDatabase
    //var adapter:HomeAdapter = HomeAdapter(context,ArrayList<mHome>())

    override fun doInBackground(vararg params: Void?): Void? {
        Log.d(Tag,"doInBackground")
        Log.d(Tag,"database.version "+database.version)
        //database.version=2
        dbHelper.onUpgrade(database,database.version,database.version+1)
        Log.d(Tag,"database.version "+database.version)
        parseJson()
        saveDB()
        readDB()
        return null
    }
    private fun readDB() {
        dbHomes=dbHelper.readHome()
    }
    private fun saveDB() {
        mHomes.forEach {
            var cvHome = ContentValues()
            cvHome.put(HOME_COL_INDIHOME, it.indiHome)
            cvHome.put(HOME_COL_NAMEHOME, it.nameHome)
            cvHome.put(HOME_COL_FLOORS, it.floors)
            cvHome.put(HOME_COL_DEVELOPERNAME, it.developerName)
            database.insert(TABLE_NAME_HOME,null,cvHome)

        }



        mApartments.forEach {
            var cvApartments = ContentValues()
            cvApartments.put(APARTMENT_COL_INDIAPARTMENT, it.indiApartment)
            cvApartments.put(APARTMENT_COL_INDIHOME, it.indiHome)
            cvApartments.put(APARTMENT_COL_FLOOR, it.floor)
            cvApartments.put(APARTMENT_COL_AREA, it.area)
            cvApartments.put(APARTMENT_COL_VISIBLE, 0)
            database.insert(TABLE_NAME_APARTMENT,null,cvApartments)
        }

    }
    private fun parseJson() {
        val mJsonObject = JSONObject(readFile())
        mData = getData(mJsonObject.getJSONObject("data"))
        Log.d(Tag,"data "+ mData.vers)

        mHomes = getHomes(mJsonObject.getJSONArray("home"))
        mApartments = getApartments(mJsonObject.getJSONArray("apartment"))
    }
    private fun getApartments(jsonArray: JSONArray): ArrayList<Apartment> {
        var apartments = ArrayList<Apartment>()
        var x =0
        while(x<jsonArray.length()){

            apartments.add(Apartment(
                    jsonArray.getJSONArray(x).getInt(0),
                    jsonArray.getJSONArray(x).getInt(1),
                    jsonArray.getJSONArray(x).getInt(2),
                    jsonArray.getJSONArray(x).getString(3).toFloat()
            ))
            x++
        }
        return apartments
    }
    private fun getHomes(jsonArray: JSONArray): ArrayList<Home> {
        var homes = ArrayList<Home>()
        var x =0
        while(x<jsonArray.length()){

            homes.add(Home(
                    jsonArray.getJSONArray(x).getInt(0),
                    jsonArray.getJSONArray(x).getString(1),
                    jsonArray.getJSONArray(x).getInt(2),
                    jsonArray.getJSONArray(x).getString(3)
            ))
            x++
        }
        return homes
    }
    private fun getData(jsonObject: JSONObject):Data{
        return Data(
                jsonObject.getInt("vers")
        )
    }
    private fun readFile(): String {
        var jsonString = stream.readBytes().toString(Charset.defaultCharset())
        //Log.d(Tag,jsonString)
        return jsonString
    }
    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        Log.d(Tag,"onPostExecute")
        var adapter = HomeAdapter(context,dbHomes)
        lvHome.adapter=adapter
    }

    override fun onPreExecute() {
        super.onPreExecute()
        Log.d(Tag,"onPreExecute")
    }
}