package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelfHelp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.youarewell)


        //hyperlink
        findViewById<Button>(R.id.image).setOnClickListener {
            val url = "https://www.psychologytoday.com/us/blog/click-here-happiness/201812/self-care-12-ways-take-better-care-yourself"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

        //back to main page
        val actionbar = supportActionBar

        actionbar!!.title = "Go back"

        actionbar.setDefaultDisplayHomeAsUpEnabled(true)


    }
}