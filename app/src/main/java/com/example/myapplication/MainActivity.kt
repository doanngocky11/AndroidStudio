package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtAge = findViewById<EditText>(R.id.edtAge)
        val btnSend = findViewById<Button>(R.id.btnSend)

        btnSend.setOnClickListener {

            val name = edtName.text.toString()
            val age = edtAge.text.toString()

            val intent = Intent(this, SecondActivity::class.java)

            val bundle = Bundle()
            bundle.putString("name", name)
            bundle.putString("age", age)

            intent.putExtras(bundle)

            startActivity(intent)
        }
    }
}