package com.example.juegita.snake

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

data class State(val food: Pair<Int, Int>, val snake: List<Pair<Int, Int>>)
class Game(private val scope: CoroutineScope) {
    private val mutex = Mutex()
    private val mutableState =
        MutableStateFlow(State(food = Pair(5, 5), snake = listOf(Pair(7, 7))))
    val state: Flow<State> = mutableState

    var move = Pair(1, 0)
        set(value) {
            scope.launch {
                mutex.withLock {
                    field = value
                }
            }
        }

    private var isPaused = false

    init {
        scope.launch {
            var snakeLength = 4
            while (true) {
                delay(150)
                if (isPaused) continue // Salta esta iteración si el juego está pausado

                mutableState.update {
                    val newPosition = it.snake.first().let { poz ->
                        mutex.withLock {
                            Pair(
                                (poz.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                                (poz.second + move.second + BOARD_SIZE) % BOARD_SIZE
                            )
                        }
                    }

                    if (newPosition == it.food) {
                        snakeLength++
                    }

                    if (it.snake.contains(newPosition)) {
                        snakeLength = 4
                    }

                    it.copy(
                        food = if (newPosition == it.food) Pair(
                            java.util.Random().nextInt(BOARD_SIZE),
                            java.util.Random().nextInt(BOARD_SIZE)
                        ) else it.food,
                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1)
                    )
                }
            }
        }
    }

    fun pauseGame(pause: Boolean) {
        isPaused = pause
    }

    companion object {
        const val BOARD_SIZE = 16
    }
}
/*
@Composable
fun Snake(game: Game, navController: NavHostController) {
    val state = game.state.collectAsState(initial = null)
    var score by remember { mutableStateOf(0) }
    var highScore by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Espacio entre la barra de estado y la barra de puntuación
        Spacer(modifier = Modifier.height(24.dp))

        // Barra de puntuación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Puntaje: $score", color = Color.Black)
            Text("Puntaje más alto: $highScore", color = Color.Black)
        }

        // Tablero de juego
        state.value?.let {
            // Actualiza el puntaje si el estado cambia
            LaunchedEffect(it.snake.size) {
                if (!isPaused) {
                    score = it.snake.size - 4
                    if (score > highScore) highScore = score
                }
            }
            Board(it)
        }

        // Controles del juego
        Buttons {
            if (!isPaused) game.move = it
        }

        // Botones adicionales: Pausa y menú principal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                isPaused = !isPaused
                game.pauseGame(isPaused) // Pausar o reanudar el juego
            }) {
                Text(if (isPaused) "Reanudar" else "Pausar")
            }
            Button(onClick = { navController.navigate("minijuegos") }) {
                Text("Volver al menú principal")
            }
        }
        }
}*/
@Composable
fun Snake(game: Game, navController: NavHostController) {
    val state = game.state.collectAsState(initial = null)
    var score by remember { mutableStateOf(0) }
    var highScore by remember { mutableStateOf(0) }
    var isPaused by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        // Espacio entre la barra de estado y la barra de puntuación
        Spacer(modifier = Modifier.height(16.dp))

        // Barra de puntuación
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Puntaje: $score", color = Color.Black)
            Text("Puntaje más alto: $highScore", color = Color.Black)
        }

        // Tablero de juego
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f) // Asegura que sea cuadrado
        ) {
            state.value?.let {
                // Actualiza el puntaje si el estado cambia
                LaunchedEffect(it.snake.size) {
                    if (!isPaused) {
                        score = it.snake.size - 4
                        if (score > highScore) highScore = score
                    }
                }
                Board(it)
            }
        }

        // Controles del juego
        Buttons {
            if (!isPaused) game.move = it
        }

        // Botones adicionales: Pausa y menú principal
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                isPaused = !isPaused
                game.pauseGame(isPaused) // Pausar o reanudar el juego
            }) {
                Text(if (isPaused) "Reanudar" else "Pausar")
            }
            Button(onClick = { navController.navigate("minijuegos") }) {
                Text("Volver al menú principal")
            }
        }
    }
}
@Composable
fun Buttons(onDirectionChange: (Pair<Int, Int>) -> Unit) {
    val buttonSize = Modifier.size(64.dp)
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(24.dp)) {
        Button(onClick = { onDirectionChange(Pair(0, -1)) }, modifier = buttonSize) {
            Icon(Icons.Default.KeyboardArrowUp, null)
        }
        Row {
            Button(onClick = { onDirectionChange(Pair(-1, 0)) }, modifier = buttonSize) {
                Icon(Icons.Default.KeyboardArrowLeft, null)
            }
            Spacer(modifier = buttonSize)
            Button(onClick = { onDirectionChange(Pair(1, 0)) }, modifier = buttonSize) {
                Icon(Icons.Default.KeyboardArrowRight, null)
            }
        }
        Button(onClick = { onDirectionChange(Pair(0, 1)) }, modifier = buttonSize) {
            Icon(Icons.Default.KeyboardArrowDown, null)
        }
    }
}

@Composable
fun Board(state: State) {
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val tileSize = maxWidth / Game.BOARD_SIZE

        // Dibuja la cuadrícula con un patrón de tablero de ajedrez
        for (row in 0 until Game.BOARD_SIZE) {
            for (col in 0 until Game.BOARD_SIZE) {
                val isLightTile = (row + col) % 2 == 0
                val tileColor = if (isLightTile) Color(0xFFA1E88B) else Color(0xFF108300) // Azul claro y oscuro

                Box(
                    Modifier
                        .offset(x = tileSize * col, y = tileSize * row)
                        .size(tileSize)
                        .background(tileColor)
                )
            }
        }

        // Dibuja la comida
        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(Color.Red, CircleShape)
        )

        // Dibuja la serpiente
        state.snake.forEach {
            Box(
                Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(Color.Blue, RoundedCornerShape(4.dp))
            )
        }
    }
}
/*
@Composable
fun Board(state: State) {
    BoxWithConstraints(Modifier.padding(16.dp)) {
        val tileSize = maxWidth / Game.BOARD_SIZE
        val Shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(4.dp),
            large = RoundedCornerShape(0.dp)
        )

        Box(
            Modifier
                .size(maxWidth)
                .border(2.dp, Color.DarkGray)
        )

        Box(
            Modifier
                .offset(x = tileSize * state.food.first, y = tileSize * state.food.second)
                .size(tileSize)
                .background(
                    Color.Red
                    , CircleShape
                )
        )

        state.snake.forEach {
            Box(
                modifier = Modifier
                    .offset(x = tileSize * it.first, y = tileSize * it.second)
                    .size(tileSize)
                    .background(
                        Color.Blue
                        , Shapes.small
                    )
            )
        }
    }
}*/
@Preview(showBackground = true)
@Composable
fun PreviewSnakeGame() {
    val scope = rememberCoroutineScope()
    val game = remember { Game(scope) }
    val navController = rememberNavController()
    Snake(game = game, navController = navController)
}