package com.lucaslima.blueguard.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.lucaslima.blueguard.R

class CadastroActivity : AppCompatActivity() {

    private lateinit var btnCadastrar: Button
    private lateinit var editEmail: EditText
    private lateinit var editUser: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmaPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)


        btnCadastrar = findViewById(R.id.btnCadastrar)
        editEmail = findViewById(R.id.editEmailCadastro)
        editUser = findViewById(R.id.editUserCadastro)
        editPassword = findViewById(R.id.editPasswordCadastro)
        editConfirmaPassword = findViewById(R.id.editConfirmaPasswordCadastro)

        btnCadastrar.setOnClickListener {

            val i = Intent(this, LoginActivity::class.java)
            val email = editEmail.text.toString()
            val usuario = editUser.text.toString()
            val senha = editPassword.text.toString()
            val confirmaSenha = editConfirmaPassword.text.toString()

            if(email.isNotEmpty() && usuario.isNotEmpty() && senha.isNotEmpty() && confirmaSenha.isNotEmpty() &&senha == confirmaSenha){
                Toast.makeText(
                    this,
                    "Cadastro realizado com sucesso!",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(i)
            }else if(email.isNotEmpty() && usuario.isNotEmpty() && senha.isNotEmpty() && confirmaSenha.isNotEmpty() && senha != confirmaSenha){
                Toast.makeText(
                    this,
                    "Senhas diferentes!",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "Por favor, preencha todos os campos!",
                    Toast.LENGTH_LONG
                ).show()
            }

        }

    }
}