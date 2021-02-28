package com.example.drurepair.presentation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.drurepair.R
import com.example.drurepair.presentation.main.MainActivity
import com.example.drurepair.request.LoginRequest
import com.example.drurepair.ui.BaseActivity
import com.example.drurepair.ui.hideSoftKeyboard
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Btn_Login.setOnClickListener {
            val username = Edt_Username.text.toString().trim()
            val password = Edt_Password.text.toString().trim()
            val req = LoginRequest(username, password)
            val result = dataSource.login(req)
            Toast.makeText(baseContext, ""+result, Toast.LENGTH_SHORT).show()
            if (result.success){
                // TODO: 12/10/2020 goto main
                val preferences = getSharedPreferences("file", Context.MODE_PRIVATE)
                result.userId?.let {
                    preferences.edit()
                        .putInt("userId", it)
                        .apply()
                }
                startActivity(Intent(baseContext, MainActivity::class.java))
            }else{
                Toast.makeText(baseContext, "ตรวจสอบรหัสอีกครั้ง", Toast.LENGTH_SHORT).show()
            }
        }
        root_Layout.setOnClickListener { hideSoftKeyboard() }
    }

    companion object{
        private const val TAG = "LoginActivity"
    }

}