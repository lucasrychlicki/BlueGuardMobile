package com.lucaslima.blueguard.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucaslima.blueguard.R


data class Relato(val usuario: String, val text: String)
class RelatosAdapter (private val relatos: MutableList<Relato>) : RecyclerView.Adapter<RelatosAdapter.RelatoViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RelatoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_relato, parent, false)
        return RelatoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RelatoViewHolder, position: Int) {
        val relato = relatos[position]
        holder.textViewUsername.text = relato.usuario
        holder.textViewRelato.text = relato.text
    }

    override fun getItemCount(): Int {
        return relatos.size
    }

    fun addRelatos(newRelatos: List<Relato>) {
        relatos.addAll(newRelatos)
        notifyDataSetChanged()
    }

    class RelatoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewUsername: TextView = itemView.findViewById(R.id.TextViewUsername)
        val textViewRelato: TextView = itemView.findViewById(R.id.TextViewRelato)
    }


}