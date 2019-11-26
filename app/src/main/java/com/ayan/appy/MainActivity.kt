package com.ayan.appy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.ayan.appy.maps.Gmap
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toastbutton.setOnClickListener{
            Log.i("MainActivity", "Toaster activated")
            Toast.makeText(applicationContext, "Toaster Activated", Toast.LENGTH_SHORT).show()
        }

        switcher.setOnClickListener {
            //Toast.makeText(applicationContext, "Switching", Toast.LENGTH_LONG).show()

            val message:String = textval.text.toString()
            Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()

            val intent = Intent(this, MapActivity::class.java)
            //intent.putExtra("key", "Ayan "+message)
            startActivity(intent)
        }

        gmap.setOnClickListener {
            startActivity(Intent(this, Gmap::class.java))
        }
    }
}
