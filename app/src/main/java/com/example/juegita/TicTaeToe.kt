package com.example.juegita


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicTacToeGame(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Cyan, Color.Magenta)
                        Text(
                            text = "Tic Tac Toe",
                            color = Color.Black,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.Center),
                            style = TextStyle(
                                brush = Brush.linearGradient(colors = gradientColors)
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("minijuegos") }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF44105b),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        // Contenido del juego
        Box(modifier = Modifier.padding(paddingValues)) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.White
            ) {
                // Imagen de fondo
                Image(
                    painter = painterResource(id = R.drawable.fnfnf),
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
                        painter = painterResource(id = R.drawable.ic_app_title),
                        contentDescription = null,
                        modifier = Modifier
                            .size(128.dp)
                            .padding(bottom = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    TicTacToeBoard()

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Reiniciar juego */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF44105b))
                    ) {
                        Text("Restart Game", color = Color.White)
                    }
                }
            }
        }
    }
}
@Composable
fun TicTacToeBoard() {
    val board = Array(3) { Array(3) { "" } }
    LazyColumn {
        items(board.size) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in board[row].indices) {
                    TicTacToeCell(
                        player = board[row][col]
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}
@Composable
fun TicTacToeCell(player: String) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(Color(0xFF44105b))
            .clickable { /* Manejar clic aquí */ }
            .padding(4.dp)
            .clip(RectangleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TicTacToeGamePreview() {
    TicTacToeGame(navController = rememberNavController())
}
