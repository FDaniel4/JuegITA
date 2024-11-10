package com.example.juegita.interfaces

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.R
import com.example.juegita.components.IconsButton
import com.example.juegita.components.Texts
import com.example.juegita.components.TitleBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectInfoScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Acerca de")
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Texts(name = "JuegITA es un proyecto  desarrollado por el equipo 6 de la materia de " +
                    "desarrollo de aplicaciones móviles, que contiene varios mini-juegos " +
                    "divertidos y desafiantes, creados para ofrecer entretenimiento y retos a " +
                    "sus usuarios.", fontSize = 25
            )

            Spacer(modifier = Modifier.height(16.dp))

            Texts(name = "Videojuegos Incluidos:", fontSize = 20)

            GameItem(name = "Tic-Tac-Toe", description = "Un clásico juego de tres en línea.")
            GameItem(name = "Memorama", description = "Pon a prueba tu memoria con este divertido juego.")
            GameItem(name = "Ahorcado", description = "Adivina la palabra antes de que se complete el ahorcado.")
        }
    }
}

@Composable
fun GameItem(name: String, description: String) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.controller),
            contentDescription = null,
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
        )
        Text(
            text = name,
            modifier = Modifier
                .padding(start = 8.dp, top = 5.dp),
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            modifier = Modifier
                .padding(start = 8.dp, top = 5.dp, bottom = 5.dp),
            textAlign = TextAlign.Center,
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Preview(showBackground = true)
@Composable
fun ProjectInfoScaffoldPreview() {
    ProjectInfoScreen(rememberNavController())
}
