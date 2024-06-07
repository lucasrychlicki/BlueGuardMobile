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

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogar: Button
    private lateinit var editUser: EditText
    private lateinit var editPassword: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        btnLogar = findViewById(R.id.btnLogar)
        editUser = findViewById(R.id.editUserLogin)
        editPassword = findViewById(R.id.editPasswordLogin)

        btnLogar.setOnClickListener {

            val i = Intent(this, AppActivity::class.java)
            val login = editUser.text.toString()
            val senha = editPassword.text.toString()

            val usuariosJson = sharedPreferences.getString("users", "[]")
            val usuarioType = object : TypeToken<MutableList<Usuario>>() {}.type
            val usuarios: MutableList<Usuario> = Gson().fromJson(usuariosJson, usuarioType)

            val usuario = usuarios.find { it.usuario == login && it.senha == senha }

            if (usuario != null) {
                sharedPreferences.edit().putString("logadoInUsuario", login).apply()
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show()
                startActivity(i)
            }else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_LONG).show()
            }

            /*val loginSalvo = sharedPreferences.getString("username", null)
            val senhaSalva = sharedPreferences.getString("password", null)

            if(login == loginSalvo && senha == senhaSalva){
                Toast.makeText(this, "Login realizado com sucesso!", Toast.LENGTH_LONG).show()
                startActivity(i)
                finish()
            }else {
                Toast.makeText(this, "Credenciais inválidas!", Toast.LENGTH_LONG).show()
            }*/
        }

    }
}