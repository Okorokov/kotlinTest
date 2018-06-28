package com.example.hpsus.kotlintest.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.hpsus.kotlintest.model.mApartment
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

    val Tag: String = "DatabaseHelper"

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
    fun readApartment(indiHome: String): ArrayList<mApartment> {
        val apartmentList = ArrayList<mApartment>()
        val selectQuery = "SELECT * FROM $TABLE_NAME_APARTMENT WHERE $APARTMENT_COL_INDIHOME LIKE $indiHome"
        Log.d(Tag,"selectQuery "+selectQuery)
        val db:SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery,null)
        Log.d(Tag,"cursor.count "+cursor.count)

       if(cursor.moveToFirst()){
            do {
                val mApartment=mApartment()
                mApartment.idApartment=cursor.getInt(cursor.getColumnIndex(APARTMENT_COL_ID))
                mApartment.indiApartment=cursor.getInt(cursor.getColumnIndex(APARTMENT_COL_INDIAPARTMENT))
                mApartment.indiHome=cursor.getInt(cursor.getColumnIndex(APARTMENT_COL_INDIHOME))
                mApartment.floor=cursor.getInt(cursor.getColumnIndex(APARTMENT_COL_FLOOR))
                mApartment.area=cursor.getDouble(cursor.getColumnIndex(APARTMENT_COL_AREA))
                mApartment.visible=cursor.getInt(cursor.getColumnIndex(APARTMENT_COL_VISIBLE))
                if(mApartment.visible!=1){
                    apartmentList.add(mApartment)
                }

            }while(cursor.moveToNext())
        }
        return apartmentList
    }

    override fun onDowngrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db!!.version=newVersion
    }

    fun writeApartment(indiApartment: String){
        val db:SQLiteDatabase = this.writableDatabase
        var cvApartments = ContentValues()
        cvApartments.put(APARTMENT_COL_VISIBLE, 1)
        db.update(TABLE_NAME_APARTMENT,cvApartments,APARTMENT_COL_INDIAPARTMENT+"="+indiApartment,null)
        //db.insert(TABLE_NAME_APARTMENT,null,cvApartments)
    }
}