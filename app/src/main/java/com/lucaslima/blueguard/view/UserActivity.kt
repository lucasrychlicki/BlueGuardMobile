package com.lucaslima.blueguard.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lucaslima.blueguard.R
import com.lucaslima.blueguard.model.Usuario

class UserActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textViewUsername: TextView
    private lateinit var textViewEmailUser: TextView
    private lateinit var fabMenu: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        textViewEmailUser = findViewById(R.id.textViewEmailUser)
        textViewUsername = findViewById(R.id.textViewUsername)
        fabMenu = findViewById(R.id.fabMenu)

        fabMenu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            startActivity(i)
        }

        val logadoInUsername = sharedPreferences.getString("logadoInUsuario", null)
        if(logadoInUsername != null) {
            val usuariosJson = sharedPreferences.getString("users", "[]")
            val usuarioType = object : TypeToken<MutableList<Usuario>>() {}.type
            val usuarios: MutableList<Usuario> = Gson().fromJson(usuariosJson, usuarioType)

            val usuario = usuarios.find { it.usuario == logadoInUsername }
            if (usuario != null) {
                textViewUsername.text = usuario.usuario
                textViewEmailUser.text = usuario.email
            }
        }
    }
}