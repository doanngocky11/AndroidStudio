
package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.graphics.Color
import android.content.res.ColorStateList
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPhone: EditText
    private lateinit var edtMsv: EditText
    private lateinit var edtClass: EditText
    private lateinit var btnRegister: Button

    private val green = Color.rgb(22, 163, 74)
    private val red = Color.rgb(220, 38, 38)


    private val touched = mutableSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtName = findViewById(R.id.edtName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPhone = findViewById(R.id.edtPhone)
        edtMsv = findViewById(R.id.edtMsv)
        edtClass = findViewById(R.id.edtClass)
        btnRegister = findViewById(R.id.btnRegister)

        setupValidation(edtName, "name")
        setupValidation(edtEmail, "email")
        setupValidation(edtPhone, "phone")
        setupValidation(edtMsv, "msv")
        setupValidation(edtClass, "class")

        btnRegister.setOnClickListener {

            val fields = listOf(
                edtName, edtEmail, edtPhone,
                edtMsv, edtClass
            )

            fields.forEach { touched.add(it.id) }

            fields.forEach { validateField(it) }

            if (fields.all { isValid(it) }) {
                val intent = Intent(
                    this,
                    SecondActivity::class.java
                )

                intent.putExtra(
                    "name",
                    edtName.text.toString().trim()
                )
                intent.putExtra(
                    "email",
                    edtEmail.text.toString().trim()
                )
                intent.putExtra(
                    "phone",
                    edtPhone.text.toString().trim()
                )
                intent.putExtra(
                    "msv",
                    edtMsv.text.toString().trim()
                )
                intent.putExtra(
                    "studentClass",
                    edtClass.text.toString().trim()
                )

                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Vui lòng sửa các thông tin chưa hợp lệ!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupValidation(
        editText: EditText,
        type: String
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {}

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                touched.add(editText.id)
                validateField(editText)
            }

            override fun afterTextChanged(s: Editable?) {}
        })


        editText.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                touched.add(editText.id)
                validateField(editText)
            }
        }
    }

    private fun isValid(editText: EditText): Boolean {
        val value = editText.text.toString().trim()

        return when (editText.id) {
            R.id.edtName ->
                value.isNotEmpty() &&
                        value.matches(Regex(".*\\p{L}.*"))

            R.id.edtEmail ->
                Patterns.EMAIL_ADDRESS.matcher(value).matches()

            R.id.edtPhone ->
                value.matches(Regex("^0[0-9]{9}$"))

            R.id.edtMsv ->
                value.matches(Regex("^[a-zA-Z0-9]{3,}$"))

            R.id.edtClass ->
                value.isNotEmpty()

            else -> false
        }
    }

    private fun validateField(editText: EditText) {
        if (!touched.contains(editText.id)) return

        val value = editText.text.toString().trim()
        val valid = isValid(editText)


        editText.backgroundTintList =
            ColorStateList.valueOf(if (valid) green else red)

        // Nội dung lỗi cụ thể
        val errorMessage = when (editText.id) {
            R.id.edtName ->
                if (value.isEmpty()) "Vui lòng nhập họ tên"
                else if (!valid) "Họ tên phải chứa chữ cái"
                else null

            R.id.edtEmail ->
                if (value.isEmpty()) "Vui lòng nhập email"
                else if (!valid) "Email không đúng định dạng"
                else null

            R.id.edtPhone ->
                if (value.isEmpty()) "Vui lòng nhập số điện thoại"
                else if (!valid) "SĐT phải gồm 10 số, bắt đầu bằng 0"
                else null

            R.id.edtMsv ->
                if (value.isEmpty()) "Vui lòng nhập MSV"
                else if (!valid) "MSV cần ít nhất 3 chữ cái hoặc chữ số"
                else null

            R.id.edtClass ->
                if (value.isEmpty()) "Vui lòng nhập lớp"
                else null

            else -> null
        }

        editText.error = errorMessage
    }
}
