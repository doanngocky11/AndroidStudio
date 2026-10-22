
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtMsv: EditText
    private lateinit var edtClass: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        edtMsv = findViewById(R.id.edtMsv)
        edtClass = findViewById(R.id.edtClass)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            registerStudent()
        }
    }

    private fun registerStudent() {
        val name = edtName.text.toString().trim()
        val email = edtEmail.text.toString().trim()
        val phone = edtPhone.text.toString().trim()
        val msv = edtMsv.text.toString().trim()
        val studentClass = edtClass.text.toString().trim()

        var valid = true


        if (name.isEmpty() ||
            !name.matches(Regex(".*\\p{L}.*"))) {
            edtName.error = "Họ tên không được trống"
            valid = false
        }


        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.error = "Email không đúng định dạng"
            valid = false
        }


        if (!phone.matches(Regex("^0[0-9]{9}$"))) {
            edtPhone.error = "SĐT phải gồm 10 số, bắt đầu bằng 0"
            valid = false
        }


        if (!msv.matches(Regex("^[a-zA-Z0-9]{3,}$"))) {
            edtMsv.error = "MSV cần ít nhất 3 chữ cái hoặc chữ số"
            valid = false
        }


        if (studentClass.isEmpty()) {
            edtClass.error = "Vui lòng nhập lớp"
            valid = false
        }

        if (valid) {
            val intent = Intent(this, SecondActivity::class.java)

            intent.putExtra("name", name)
            intent.putExtra("email", email)
            intent.putExtra("phone", phone)
            intent.putExtra("msv", msv)
            intent.putExtra("studentClass", studentClass)

            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Vui lòng kiểm tra lại thông tin!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
