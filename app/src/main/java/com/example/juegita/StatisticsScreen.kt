package com.example.juegita

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = PlayerAdapter()
        recyclerView.adapter = adapter

        // Botón para añadir jugador (solo visual)
        val addPlayerButton: Button = findViewById(R.id.addPlayerButton)
        addPlayerButton.setOnClickListener {
            // Aquí iría la lógica para añadir un jugador
        }
    }
}

private fun Any.onCreate(savedInstanceState: Bundle?) {
    TODO("Not yet implemented")
}

class PlayerAdapter : RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder>() {
    class PlayerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.findViewById(R.id.playerName)
        val scoreTextView: TextView = view.findViewById(R.id.playerScore)
        val gamesPlayedTextView: TextView = view.findViewById(R.id.gamesPlayed)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        // Aquí solo pondremos datos de ejemplo
        holder.nameTextView.text = "Jugador ${position + 1}"
        holder.scoreTextView.text = "Puntaje: ${1000 - position * 100}"
        holder.gamesPlayedTextView.text = "Partidas jugadas: ${10 - position}"
    }

    override fun getItemCount() = 5 // Mostraremos 5 jugadores de ejemplo
}
