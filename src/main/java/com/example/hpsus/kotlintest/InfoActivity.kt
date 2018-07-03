package com.example.hpsus.kotlintest

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.database.sqlite.SQLiteException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import com.example.hpsus.kotlintest.model.mHome
import com.example.hpsus.kotlintest.sqlite.HOME_COL_DEVELOPERNAME
import com.example.hpsus.kotlintest.sqlite.HOME_COL_FLOORS
import com.example.hpsus.kotlintest.sqlite.HOME_COL_INDIHOME
import com.example.hpsus.kotlintest.sqlite.HOME_COL_NAMEHOME
import java.text.SimpleDateFormat
import java.util.*

class InfoActivity : AppCompatActivity() {
    val Tag: String = "InfoActivity"
    var mHomeIA: mHome = mHome()
    var prHomeIA: mHome = mHome()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        var intent = intent
        //Log.d(Tag, "KEY_INFO_NAMBERPUT- " + intent.getStringExtra(DBHelper.KEY_INFO_NAMBERPUT))
        supportActionBar!!.setTitle("Дом " + intent.getStringExtra(HOME_COL_NAMEHOME))


        mHomeIA.indiHome = intent.getIntExtra(HOME_COL_INDIHOME, 0)
        mHomeIA.nameHome = intent.getStringExtra(HOME_COL_NAMEHOME)
        mHomeIA.floors = intent.getIntExtra(HOME_COL_FLOORS, 0)
        mHomeIA.developerName = intent.getStringExtra(HOME_COL_DEVELOPERNAME)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            16908332 -> {
                onBackPressed()
                return true
            }
            R.id.action_info -> {
                showDialog()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun showDialog() {
        val li = LayoutInflater.from(this)
        val promptsView = li.inflate(R.layout.dialog_info, null)
        val mDialogBuilder = AlertDialog.Builder(this)
        mDialogBuilder.setView(promptsView)
        var tvindiHomeText = promptsView.findViewById(R.id.tvindiHomeText) as EditText
        val tvnameHomeText = promptsView.findViewById(R.id.tvnameHomeText) as EditText
        val tvfloorsText = promptsView.findViewById(R.id.tvfloorsText) as EditText
        val tvdeveloperText = promptsView.findViewById(R.id.tvdeveloperText) as EditText

        tvindiHomeText.setText(mHomeIA.indiHome)
        tvnameHomeText.setText(mHomeIA.nameHome)
        tvfloorsText.setText(mHomeIA.floors)
        tvdeveloperText.setText(mHomeIA.developerName)

        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("ОК") { dialog, which -> }


        //Создаем AlertDialog:
        val alertDialog = mDialogBuilder.create()

        //и отображаем его:
        alertDialog.show()

    }
}


