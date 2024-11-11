package com.example.juegita.snake

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Point(val x: Int, val y: Int)

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

@Composable
fun ElJuegoDeLaViborita(navController: NavHostController) {
    // Configuración del tablero
    val boardSize = 20 // Tamaño del tablero (20x20)
    val cellSize = 20.dp // Tamaño de cada celda
    val initialSnake = listOf(Point(5, 5), Point(5, 6), Point(5, 7))

    // Estados del juego
    var snake by remember { mutableStateOf(initialSnake) }
    var direction by remember { mutableStateOf(Direction.RIGHT) }
    var food by remember { mutableStateOf(generateFoodPosition(snake, boardSize)) }
    var gameOver by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    // Control del juego
    LaunchedEffect(direction) {
        while (!gameOver) {
            delay(200L) // Velocidad del juego
            snake = moveSnake(snake, direction)

            // Verificar si la serpiente ha comido el alimento
            if (snake.first() == food) {
                score += 10
                snake = listOf(food) + snake
                food = generateFoodPosition(snake, boardSize)
            } else {
                snake = listOf(snake.first()) + snake.dropLast(1)
            }

            // Verificar colisiones
            if (checkCollision(snake, boardSize)) {
                gameOver = true
            }
        }
    }

    // Interfaz del juego
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .onPreviewKeyEvent { keyEvent ->
                // Control del movimiento de la serpiente con teclas de dirección
                when (keyEvent.key) {
                    Key.DirectionUp -> direction = Direction.UP
                    Key.DirectionDown -> direction = Direction.DOWN
                    Key.DirectionLeft -> direction = Direction.LEFT
                    Key.DirectionRight -> direction = Direction.RIGHT
                }
                true
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (gameOver) {
            Text("Juego terminado! Puntaje: $score", color = Color.Red, style = MaterialTheme.typography.headlineMedium)
        } else {
            Canvas(modifier = Modifier.size((cellSize * boardSize).value.dp)) {
                // Dibujar la serpiente
                snake.forEach {
                    drawRect(
                        color = Color.Green,
                        topLeft = androidx.compose.ui.geometry.Offset(it.x * cellSize.value, it.y * cellSize.value),
                        size = androidx.compose.ui.geometry.Size(cellSize.value, cellSize.value)
                    )
                }
                // Dibujar el alimento
                drawRect(
                    color = Color.Red,
                    topLeft = androidx.compose.ui.geometry.Offset(food.x * cellSize.value, food.y * cellSize.value),
                    size = androidx.compose.ui.geometry.Size(cellSize.value, cellSize.value)
                )
            }
        }
    }
}

// Mueve la serpiente en la dirección especificada
fun moveSnake(snake: List<Point>, direction: Direction): List<Point> {
    val head = snake.first()
    val newHead = when (direction) {
        Direction.UP -> Point(head.x, head.y - 1)
        Direction.DOWN -> Point(head.x, head.y + 1)
        Direction.LEFT -> Point(head.x - 1, head.y)
        Direction.RIGHT -> Point(head.x + 1, head.y)
    }
    return listOf(newHead) + snake.dropLast(1)
}

// Genera una posición para el alimento que no esté ocupada por la serpiente
fun generateFoodPosition(snake: List<Point>, boardSize: Int): Point {
    var newFoodPosition: Point
    do {
        newFoodPosition = Point(Random.nextInt(boardSize), Random.nextInt(boardSize))
    } while (snake.contains(newFoodPosition))
    return newFoodPosition
}

// Verifica si la serpiente colisiona con las paredes o consigo misma
fun checkCollision(snake: List<Point>, boardSize: Int): Boolean {
    val head = snake.first()
    // Colisión con paredes
    if (head.x < 0 || head.x >= boardSize || head.y < 0 || head.y >= boardSize) return true
    // Colisión con el propio cuerpo
    if (snake.drop(1).contains(head)) return true
    return false
}

@Preview(showBackground = true)
@Composable
fun ElJuegoDeLaViboritaPreview() {
    ElJuegoDeLaViborita(navController = rememberNavController())
}