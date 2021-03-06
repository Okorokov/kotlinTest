package com.example.hpsus.kotlintest.sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.hpsus.kotlintest.model.mHome

var context: Context? = null
val DB_NAME = "base.db"
val DB_VERSION = 1

val TABLE_NAME_HOME = "home"
val TABLE_NAME_APARTMENT = "apartment"

val HOME_COL_ID = "_id"
val HOME_COL_INDIHOME = "indiHome"
val HOME_COL_NAMEHOME = "nameHome"
val HOME_COL_FLOORS = "floors"
val HOME_COL_DEVELOPERNAME = "developerName"

val APARTMENT_COL_ID = "_id"
val APARTMENT_COL_INDIAPARTMENT = "indiApartment"
val APARTMENT_COL_INDIHOME = "indiHome"
val APARTMENT_COL_FLOOR = "floor"
val APARTMENT_COL_AREA = "area"
val APARTMENT_COL_VISIBLE = "visible"


class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table " + TABLE_NAME_HOME + "("
                + HOME_COL_ID + " integer primary key,"
                + HOME_COL_INDIHOME + " integer,"
                + HOME_COL_NAMEHOME + " text,"
                + HOME_COL_FLOORS + " integer,"
                + HOME_COL_DEVELOPERNAME + " text" + ")")

        db?.execSQL("create table " + TABLE_NAME_APARTMENT + "("
                + APARTMENT_COL_ID + " integer primary key,"
                + APARTMENT_COL_INDIAPARTMENT + " integer,"
                + APARTMENT_COL_INDIHOME + " integer,"
                + APARTMENT_COL_FLOOR + " integer,"
                + APARTMENT_COL_AREA + " real,"
                + APARTMENT_COL_VISIBLE + " integer" + ")")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(oldVersion<newVersion){
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_HOME")
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_APARTMENT")
            onCreate(db);
        }

    }
    fun readHome(): ArrayList<mHome> {
        val homeList = ArrayList<mHome>()
        val selectQuery = "SELECT * FROM $TABLE_NAME_HOME"
        val db:SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery,null)
        if(cursor.moveToFirst()){
            do {
                val mhome=mHome()
                mhome.idHome=cursor.getInt(cursor.getColumnIndex(HOME_COL_ID))
                mhome.indiHome=cursor.getInt(cursor.getColumnIndex(HOME_COL_INDIHOME))
                mhome.nameHome=cursor.getString(cursor.getColumnIndex(HOME_COL_NAMEHOME))
                mhome.floors=cursor.getInt(cursor.getColumnIndex(HOME_COL_FLOORS))
                mhome.developerName=cursor.getString(cursor.getColumnIndex(HOME_COL_DEVELOPERNAME))
                homeList.add(mhome)
            }while(cursor.moveToNext())
        }
        return homeList
    }

}