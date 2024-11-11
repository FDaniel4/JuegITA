package com.example.juegita.SimonSays

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController

class SimonSaysActivity  : ComponentActivity(), SoundPlayer {

    private val Context.dataStore by preferencesDataStore(name = "Simon says")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val gameViewModel: GameViewModel =
                viewModel(factory = GameViewModelFactory(dataStore, this))
            SimonSaysGame(navController = rememberNavController(), gameViewModel)
        }
    }

    override fun playSound(soundId: Int) {
        MediaPlayer.create(this, soundId)?.apply {
            start()
            setOnCompletionListener { it.release() }
        }
    }
}

class GameViewModelFactory(
    private val dataStore: DataStore<Preferences>,
    private val soundPlayer: SoundPlayer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(dataStore, soundPlayer) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}