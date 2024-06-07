package com.lucaslima.blueguard.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.lucaslima.blueguard.databinding.ActivityRelatarBinding
import com.lucaslima.blueguard.model.Usuario


class RelatarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRelatarBinding
    private lateinit var sharedPreferences: SharedPreferences
    /*private lateinit var fabMenu: FloatingActionButton
    private lateinit var editReport: EditText
    private lateinit var btnReport: Button*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRelatarBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_relatar)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        /*fabMenu = findViewById(R.id.fabMenu)
        editReport = findViewById(R.id.editReport)*/

        binding.fabMenu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            startActivity(i)
            finish()
        }

        binding.btnReport.setOnClickListener {
            val relato = binding.editReport.text.toString()
            val usuario = sharedPreferences.getString("logadoInUsuario", null)

            if(relato.isNotEmpty() && usuario != null) {
                val i = Intent(this, RelatorioActivity::class.java)
                val usuariosJson = sharedPreferences.getString("users", "[]")
                val usuarioType = object : TypeToken<MutableList<Usuario>>() {}.type
                val usuarios: MutableList<Usuario> = Gson().fromJson(usuariosJson, usuarioType)

                val usuario = usuarios.find { it.usuario == usuario }
                usuario?.relatos?.add(relato)

                sharedPreferences.edit().putString("users", Gson().toJson(usuarios)).apply()

                Toast.makeText(this, "Relato Enviado", Toast.LENGTH_SHORT).show()
                startActivity(i)
                finish()
            } else {
                Toast.makeText(this, "Escreva um relato!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}