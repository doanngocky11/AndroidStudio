package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val txtResult = findViewById<TextView>(R.id.txtResult)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val bundle = intent.extras

        val name = bundle?.getString("name")
        val age = bundle?.getString("age")

        txtResult.text = "Họ tên: $name\nTuổi: $age"

        btnBack.setOnClickListener {
            finish()
        }
    }
}