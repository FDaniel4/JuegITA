package com.example.juegita


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.juegita.Navigation.SetupNavGraph
import com.example.juegita.viewModel.GamesViewModel
import com.example.juegita.viewModel.LoginViewModel
import com.example.juegita.viewModel.NotesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: GamesViewModel by viewModels()
        val loginViewModel: LoginViewModel by viewModels()
        val notesViewModel : NotesViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            SetupNavGraph(navController = rememberNavController(), viewModel = viewModel,loginViewModel, notesViewModel)
        }
    }
}
