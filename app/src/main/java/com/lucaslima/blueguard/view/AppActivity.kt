package com.lucaslima.blueguard.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.lucaslima.blueguard.R

class AppActivity : AppCompatActivity() {

    private lateinit var fabMenu: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        fabMenu = findViewById(R.id.fabMenu)

        fabMenu.setOnClickListener {
            val i = Intent(this,MenuActivity::class.java)
            startActivity(i)
        }

    }
}