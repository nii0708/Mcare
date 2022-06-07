package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText

class Welp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wewillhelp)

        //get location
        var Intent1: Intent

        Intent1= getIntent()

        var obj   =  Intent1.getStringExtra("city")

        val dicadress = mapOf(
            "Semarang" to "\n\nRSJ Amino Gondohutomo Semarang\n\n(024) 6722565\n\nJl. Brigjen Sudiarto No.347, Gemah, Kec. Pedurungan, Kota Semarang, Jawa Tengah",
            "Jakarta" to "\n\nRSJ Soeharto Heerdjan Jakarta\n\n(021) 5682841\n\nJl. Prof. Dr. Latumeten No.1, RW.4, Jelambar, Kec. Grogol petamburan, Kota Jakarta Barat, Daerah Khusus Ibukota Jakarta",
            "Malang" to "\n\nRSJ Radjiman Wediodiningrat Malang\n\n(0341) 423444\n\nJl. A Yani No.776, Sumber Porong, Kec. Lawang, Kabupaten Malang, Jawa Timur",
            "Bogor" to "\n\nRSJ Marzoeki Mahdi Bogor\n\n(0251) 8324024, 8324025, 8320467\n\nJl. DR. Sumeru No.114, RT.02/RW.01, Menteng, Kec. Bogor Bar., Kota Bogor, Jawa Barat",
            "Magelang" to "\n\nRSJ Prof Dr Soerojo Magelang\n\n(0293) 363601\n\nJl. Ahmad Yani No.169, Kramat Utara, Kec. Magelang Utara, Kota Magelang, Jawa Tengah")

        findViewById<EditText>(R.id.clinic).setText("You need more help from:"+dicadress[obj.toString()])

        //actionbar

        val actionbar = supportActionBar

        actionbar!!.title = "Go back"

        actionbar.setDefaultDisplayHomeAsUpEnabled(true)

    }
}