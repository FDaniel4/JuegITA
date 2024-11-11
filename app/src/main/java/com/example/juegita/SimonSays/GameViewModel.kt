package com.example.juegita.SimonSays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.juegita.R
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import java.util.prefs.Preferences

interface SoundPlayer {
    fun playSound(soundId: Int)
}

class GameViewModel(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>, private val soundPlayer: SoundPlayer) : ViewModel() {

    var finalScore by mutableStateOf(0)

    private fun playSound(colorIndex: Int) {
        val soundId = when (colorIndex) {
            0 -> R.raw.app_src_main_res_raw_sound_red
            1 -> R.raw.app_src_main_res_raw_sound_green
            2 -> R.raw.app_src_main_res_raw_sound_blue
            3 -> R.raw.app_src_main_res_raw_sound_yellow
            else -> return
        }
        soundPlayer.playSound(soundId)
    }


    private val HIGH_SCORE_KEY = intPreferencesKey("high_score")

    val highScoreFlow = dataStore.data
        .map { preferences ->
            // Se devuelve la puntuación más alta almacenada o 0 si no existe
            preferences[HIGH_SCORE_KEY] ?: 0
        }

    private var sequence by mutableStateOf(listOf<Int>())
    private var currentInput by mutableStateOf(listOf<Int>())
    var gameStarted by mutableStateOf(false)
    var activeColor by mutableStateOf(-1)
    var gameMessage by mutableStateOf("Press 'Start' to play Simon Says!")
    private var sequenceKey by mutableStateOf(0) // Agregado para forzar la recomposición
    var score by mutableStateOf(0) // Variable para la puntuación

    private fun saveHighScore() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                val highScore = preferences[HIGH_SCORE_KEY] ?: 0
                if (score > highScore) {
                    preferences[HIGH_SCORE_KEY] = score
                }
            }
        }
    }

    private fun updateHighScore() {
        viewModelScope.launch {
            // Se obtiene la puntuación más alta actual del flujo de DataStore
            val highScore = highScoreFlow.first()
            if (score > highScore) {
                saveHighScore()
            }
        }
    }

    fun startGame() {
        if (!gameStarted) {
            gameStarted = true
            sequence = listOf()
            currentInput = listOf()
            score = 0
            nextSequence()
        }
    }

    // Llama a playSound en el momento apropiado en nextSequence()
    private fun nextSequence() {
        viewModelScope.launch {
            sequence += (0..3).random()
            activeColor = -1
            gameMessage = "Watch the sequence!"
            delay(1000)

            sequence.forEach { colorIndex ->
                activeColor = colorIndex
                playSound(colorIndex)
                delay(600)
                activeColor = -1
                delay(400)
            }

            gameMessage = "Your turn!"
            delay(500)
        }
    }


    fun handleUserInput(colorIndex: Int) {
        if (gameStarted && activeColor == -1 && currentInput.size < sequence.size) {
            currentInput += colorIndex
            playSound(colorIndex) // Reproducir sonido al recibir la entrada del usuario
            checkSequence()
        }
    }


    var gameOver by mutableStateOf(false)

    private fun checkSequence() {
        if (currentInput.size == sequence.size) {
            if (currentInput == sequence) {
                gameMessage = "Correct! Keep going."
                score++
                currentInput = listOf()
                sequenceKey++
                nextSequence()
            } else {
                finalScore = score // Guarda el puntaje final antes de resetear
                gameMessage = "Wrong sequence! GAME OVER."
                score = 0
                gameOver = true
                gameStarted = false
                sequence = listOf()
                currentInput = listOf()
                sequenceKey++
            }
        }
        updateHighScore()
    }


}
