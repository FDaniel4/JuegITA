package com.example.juegita.SimonSays

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlin.system.exitProcess


@Composable
fun SimonSaysGame(navController: NavController, gameViewModel: GameViewModel) {
    val highScore by gameViewModel.highScoreFlow.collectAsState(initial = 0)
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)

    HighScoreDisplay(highScore = highScore)

    if (gameViewModel.gameOver) {
        GameOverDialog(gameViewModel)
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScoreDisplay(score = gameViewModel.score)

            Spacer(modifier = Modifier.height(32.dp))

            ColorButtonsGrid(colors = colors, activeColor = gameViewModel.activeColor) { index ->
                gameViewModel.handleUserInput(index)
            }

            Spacer(modifier = Modifier.height(32.dp))

            GameMessage(message = gameViewModel.gameMessage)
            Spacer(modifier = Modifier.height(64.dp))
            StartButton(gameStarted = gameViewModel.gameStarted) {
                gameViewModel.startGame()
            }
        }
    }
}

@Composable
fun GameOverDialog(gameViewModel: GameViewModel) {
    val context = LocalContext.current  // Obtiene el contexto local aquí

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                "GAME OVER",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        },
        text = {
            Text(
                "Your final score was ${gameViewModel.finalScore}. Tap Restart to play again or Exit to close.",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center // Centra el texto horizontalmente
            )
        },
        confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        gameViewModel.startGame()
                        gameViewModel.gameOver = false
                    },
                    modifier = Modifier
                        .padding(end = 8.dp) // Añade espacio entre los botones
                        .width(120.dp)
                ) {
                    Text("Restart")
                }
                Button(
                    onClick = {

                        if (context is Activity) {
                            exitProcess(0)
                        }
                    },
                    modifier = Modifier
                        .width(120.dp)
                ) {
                    Text("Exit")
                }
            }
        },
        dismissButton = {}
    )
}

@Composable
fun HighScoreDisplay(highScore: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "High Score: $highScore",
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun ScoreDisplay(score: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$score", style = MaterialTheme.typography.displayLarge)
    }
}

@Composable
fun GameMessage(message: String) {
    Text(text = message, style = MaterialTheme.typography.titleMedium)
}

@Composable
fun StartButton(gameStarted: Boolean, onStart: () -> Unit) {
    if (!gameStarted) {
        Button(
            onClick = onStart,
            modifier = Modifier.size(width = 150.dp, height = 50.dp), // Dimensiones del botón
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue) // Color del botón
        ) {
            Text(
                text = "Start",
                color = Color.White, // Color del texto
                style = TextStyle(fontSize = 18.sp) // Aumenta el tamaño del texto
            )
        }
    }
}

@Composable
fun ColorButtonsGrid(colors: List<Color>, activeColor: Int, onColorClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // Establece dos columnas
        modifier = Modifier.padding(8.dp)
    ) {
        items(colors.size) { index ->
            val color = colors[index]
            val isCurrent = index == activeColor
            Button(
                onClick = { onColorClick(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isCurrent) color else color.copy(alpha = 0.3f)
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.background(if (isCurrent) color else color.copy(alpha = 0.3f)))
            }
        }
    }
}
