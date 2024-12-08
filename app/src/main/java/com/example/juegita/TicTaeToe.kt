package com.example.juegita

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.juegita.components.IconsButton
import com.example.juegita.components.TitleBar

// Función para copiar el tablero (deep copy)
fun copyBoard(board: Array<Array<String>>): Array<Array<String>> {
    return Array(3) { row -> Array(3) { col -> board[row][col] } }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeGame(navController: NavHostController) {
    // Estado del tablero
    var board by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
    // Estado del turno (X o O)
    var currentPlayer by remember { mutableStateOf("X") }
    // Estado para el ganador
    var winner by remember { mutableStateOf<String?>(null) }

    // Animación para el tamaño del texto del ganador
    val textSize by animateFloatAsState(
        targetValue = if (winner != null) 48f else 20f, // Grande cuando haya ganador, normal cuando no
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 500) // Animación suave
    )

    // Color azul clarito
    val blueColor = Color(0xFFFFEB3B) // Azul claro

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleBar(name = "Tic-Tac-Toe")
                },
                navigationIcon = {
                    IconsButton(icon = Icons.Default.ArrowBack) {
                        navController.navigate("minijuegos")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EA)
                )
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                // Imagen de fondo
                Image(
                    painter = painterResource(id = R.drawable.fnfnf),  // Asegúrate de que esta imagen exista
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_app_title),  // Asegúrate de que esta imagen exista
                        contentDescription = null,
                        modifier = Modifier.size(128.dp).padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Mostrar el tablero
                    TicTacToeBoard(board = board, onCellClick = { row, col ->
                        if (board[row][col].isEmpty() && winner == null) {
                            // Copia el tablero antes de modificarlo
                            val newBoard = copyBoard(board)
                            newBoard[row][col] = currentPlayer
                            board = newBoard

                            // Verifica si hay un ganador
                            winner = checkWinner(board)
                            currentPlayer = if (currentPlayer == "X") "O" else "X"
                        }
                    })

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {
                            // Reinicia el juego
                            board = Array(3) { Array(3) { "" } }
                            winner = null
                            currentPlayer = "X"
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF44105b))
                    ) {
                        Text("Restart Game", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Muestra el ganador con el texto animado
                    if (winner != null) {
                        Text(
                            text = "Player $winner Wins!",
                            fontSize = textSize.sp,  // Aplicamos el tamaño animado
                            fontWeight = FontWeight.Bold,
                            color = blueColor,  // Usamos el azul claro
                            modifier = Modifier.padding(16.dp)
                        )
                    } else {
                        Text(
                            text = "Current Turn: $currentPlayer",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = blueColor  // Usamos el azul claro
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TicTacToeBoard(board: Array<Array<String>>, onCellClick: (Int, Int) -> Unit) {
    // Usamos un Grid para mostrar las celdas en una cuadrícula 3x3
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Aquí es donde generamos las filas y celdas
        for (row in 0 until 3) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (col in 0 until 3) {
                    TicTacToeCell(player = board[row][col], onClick = { onCellClick(row, col) })
                    // Espaciado horizontal entre las celdas
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            // Espaciado vertical entre las filas
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TicTacToeCell(player: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(100.dp) // Aseguramos que cada celda sea cuadrada (100x100 dp)
            .background(Color(0xFF44105b))  // Ya no necesitamos el clip con RectangleShape
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

// Lógica para determinar el ganador
fun checkWinner(board: Array<Array<String>>): String? {
    // Revisa filas
    for (row in board) {
        if (row[0].isNotEmpty() && row[0] == row[1] && row[1] == row[2]) {
            return row[0]
        }
    }

    // Revisa columnas
    for (col in 0 until 3) {
        if (board[0][col].isNotEmpty() && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
            return board[0][col]
        }
    }

    // Revisa diagonales
    if (board[0][0].isNotEmpty() && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
        return board[0][0]
    }
    if (board[0][2].isNotEmpty() && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
        return board[0][2]
    }

    return null
}

@Preview(showBackground = true)
@Composable
fun TicTacToeGamePreview() {
    TicTacToeGame(navController = rememberNavController())
}