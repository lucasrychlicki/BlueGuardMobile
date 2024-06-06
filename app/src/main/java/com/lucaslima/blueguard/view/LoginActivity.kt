package com.lucaslima.blueguard.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.lucaslima.blueguard.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogar: Button
    private lateinit var editUser: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        btnLogar = findViewById(R.id.btnLogar)
        editUser = findViewById(R.id.editUserLogin)
        editPassword = findViewById(R.id.editPasswordLogin)

        btnLogar.setOnClickListener {

            val i = Intent(this, AppActivity::class.java)
            val login = editUser.text.toString()
            val senha = editPassword.text.toString()

            if(login.isNotEmpty() && senha.isNotEmpty()){
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show()
                startActivity(i)
            }else {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_LONG).show()
            }

        }



    }
}