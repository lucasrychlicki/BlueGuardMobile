package com.lucaslima.blueguard.model

data class Usuario(
    val usuario: String,
    val senha: String,
    val email: String,
    val relatos: MutableList<String> = mutableListOf()
)
