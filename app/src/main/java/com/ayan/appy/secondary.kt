package com.ayan.appy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_secondary.*

class secondary : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secondary)

        val bundle: Bundle? = intent.extras
        val msg = bundle!!.getString("key")
        texty.setText(msg)

        oswitcher.setOnClickListener{
            val intent = Intent()

            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, msg)
            intent.type = "text/plain"

            startActivity(Intent.createChooser(intent, "Share to "))
        }
    }
}
