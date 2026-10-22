
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondactivity)

        val txtName = findViewById<TextView>(R.id.txtName)
        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)
        val txtMsv = findViewById<TextView>(R.id.txtMsv)
        val txtClass = findViewById<TextView>(R.id.txtClass)
        val btnBack = findViewById<Button>(R.id.btnBack)


        val name = intent.getStringExtra("name") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val phone = intent.getStringExtra("phone") ?: ""
        val msv = intent.getStringExtra("msv") ?: ""
        val studentClass =
            intent.getStringExtra("studentClass") ?: ""


        txtName.text = "Họ và tên: $name"
        txtEmail.text = "Email: $email"
        txtPhone.text = "Số điện thoại: $phone"
        txtMsv.text = "Mã sinh viên: $msv"
        txtClass.text = "Lớp: $studentClass"


        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
