package com.example.hpsus.kotlintest

import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val Tag: String = "MainActivity"
    val REQUEST_READ_EXTERNAL = 1
    val DOCUMENT_NAME = "document.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL)
        }else{
            JsonAsyncTask(assets.open(DOCUMENT_NAME),this,lvHome).execute()
        }
    }
  /*  fun onClickbtnReadJson(view: MainActivity){
        //JsonAsyncTask(tvResult).execute()
    }*/
}
