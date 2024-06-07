package com.lucaslima.blueguard.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.lucaslima.blueguard.R
import com.lucaslima.blueguard.databinding.ActivityRelatorioBinding
import com.lucaslima.blueguard.model.Usuario

class RelatorioActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var relatosAdapter: RelatosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabMenu: FloatingActionButton
    private var currentPage = 0
    private val pageSize = 15
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_relatorio)

        sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        recyclerView = findViewById(R.id.recyclerViewRelatos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        relatosAdapter = RelatosAdapter(mutableListOf())
        recyclerView.adapter = relatosAdapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    carregarMaisRelatos()
                }
            }
        })

        carregarRelatos()


        fabMenu = findViewById(R.id.fabMenu)

        fabMenu.setOnClickListener {
            val i = Intent(this, MenuActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun carregarRelatos() {
        val usuariosJson = sharedPreferences.getString("users", "[]")
        val usuarioType = object : TypeToken<MutableList<Usuario>>() {}.type
        val usuarios: MutableList<Usuario> = Gson().fromJson(usuariosJson, usuarioType)

        val allRelatos = usuarios.flatMap { usuario ->
            usuario.relatos.map { report -> Relato(usuario.usuario, report) }
        }

        val inicio = currentPage * pageSize
        val final = Math.min(inicio + pageSize, allRelatos.size)
        val relatosAtuais = allRelatos.subList(inicio, final)

        relatosAdapter.addRelatos(relatosAtuais)
        currentPage++
    }

    private fun carregarMaisRelatos() {
        carregarRelatos()
    }
}