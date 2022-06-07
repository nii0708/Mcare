package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init value
        val editText1 = findViewById<EditText>(R.id.Text1)
        val editText2 = findViewById<EditText>(R.id.Text2)
        val editText3 = findViewById<EditText>(R.id.Text3)

        //seting title
        val actionbar = supportActionBar

        actionbar!!.title = ""

        //input button config
        findViewById<Button>(R.id.button1).setOnClickListener {
            // Getting the user input
            val text1 = editText1.text
            // Showing the user input
            Toast.makeText(this, text1, Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            // Getting the user input
            val text2 = editText2.text
            // Showing the user input
            Toast.makeText(this, text2, Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            // Getting the user input
            val text3 = editText3.text
            // Showing the user input
            Toast.makeText(this, text3, Toast.LENGTH_SHORT).show()
        }

        //spinner
        val city = resources.getStringArray(R.array.City)
        // access the spinner
        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, city)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@MainActivity,
                        getString(R.string.selected_item) + " " + city[position], Toast.LENGTH_SHORT).show()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }


        //predict button config
        findViewById<Button>(R.id.predict).setOnClickListener {
            val texts = Texts(
                editText1.text.toString(),
                editText2.text.toString(),
                editText3.text.toString()
            )

            val send = Config.getApiService(SendingData::class.java)
            send.PostData(texts)
                .enqueue(object : Callback<predict> {
                    override fun onFailure(call: Call<predict>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "System error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<predict>, response: Response<predict>) {
                        if (response.isSuccessful) {
                                var gson = Gson()
                                var resp = gson.toJson(response.body(), predict::class.java)

                                val status = resp.get(11)
                                //if need help
                                if (status.toString().equals("1") ) {
                                    val intent = Intent(this@MainActivity,Welp::class.java)
                                    intent.putExtra("city",spinner.selectedItem.toString())
                                    startActivity(intent)
                                //if (need help) complement
                                }
                                if (status.toString().equals("0") ) {
                                    val intent = Intent(this@MainActivity,SelfHelp::class.java)
                                    startActivity(intent)
                                }
                        }
                    }
                })
        }
    }
}











