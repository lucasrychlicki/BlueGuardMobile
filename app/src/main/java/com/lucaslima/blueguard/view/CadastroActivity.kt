package com.lucaslima.blueguard.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.lucaslima.blueguard.R
import com.lucaslima.blueguard.model.Usuario

class CadastroActivity : AppCompatActivity() {

    private lateinit var btnCadastrar: Button
    private lateinit var editEmail: EditText
    private lateinit var editUser: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmaPassword: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

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

            if(email.isNotEmpty() && usuario.isNotEmpty() && senha.isNotEmpty() && confirmaSenha.isNotEmpty() && senha == confirmaSenha){

                val usuariosJson = sharedPreferences.getString("users", "[]")
                val usuarioType = object : TypeToken<MutableList<Usuario>>() {}.type
                val usuarios: MutableList<Usuario> = Gson().fromJson(usuariosJson, usuarioType)

                val usuario = Usuario(usuario, senha, email)
                usuarios.add(usuario)

                sharedPreferences.edit().putString("users", Gson().toJson(usuarios)).apply()

                /*val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("username", usuario)
                editor.putString("password", senha)
                editor.apply()*/

                Toast.makeText(
                    this,
                    "Cadastro realizado com sucesso!",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(i)
                finish()
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