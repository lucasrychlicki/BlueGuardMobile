package com.lucaslima.blueguard.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucaslima.blueguard.R
import com.lucaslima.blueguard.databinding.ActivityMainBinding
import com.lucaslima.blueguard.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_menu)

        binding.textViewSobre.setOnClickListener {
            val i = Intent(this, AppActivity::class.java)
            startActivity(i)
        }

        binding.textViewRelatorio.setOnClickListener {
            val i = Intent(this, RelatorioActivity::class.java)
            startActivity(i)
        }

        binding.textViewUser.setOnClickListener {
            val i = Intent(this, UserActivity::class.java)
            startActivity(i)
        }

    }
}