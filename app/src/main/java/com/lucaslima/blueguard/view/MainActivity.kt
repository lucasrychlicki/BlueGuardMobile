package com.lucaslima.blueguard.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.lucaslima.blueguard.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnLogin: Button
    private lateinit var btnCadastro: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnLogin = findViewById(R.id.btnLogin)
        btnCadastro = findViewById(R.id.btnCadastro)

        btnLogin.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        btnCadastro.setOnClickListener {
            val i = Intent(this, CadastroActivity::class.java)
            startActivity(i)
        }

    }
}