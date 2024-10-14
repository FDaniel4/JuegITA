package com.example.juegita
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class TicTaeToe : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeGame()
        }
    }
}
@Composable
fun TicTacToeGame() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White, // Cambia a tu color de fondo deseado
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fnfnf), // Cambia "img" al nombre de tu imagen
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Reemplaza el texto por una imagen
            Image(
                painter = painterResource(id = R.drawable.ic_app_title), // Cambia "ic_app_title" al nombre de tu imagen
                contentDescription = null,
                modifier = Modifier
                    .size(128.dp) // Ajusta el tamaño según sea necesario
                    .padding(bottom = 16.dp) // Espaciado inferior
            )

            Spacer(modifier = Modifier.height(16.dp))

            TicTacToeBoard()

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Reiniciar juego */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray) // Cambia el color según sea necesario
            ) {
                Text("Restart Game")
            }
        }
    }
}
@Composable
fun TicTacToeBoard() {
    val board = Array(3) { Array(3) { "" } } // Tablero vacío
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
            .background(Color.Blue) // Cambia a tu color de caja deseado
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
@Preview(showBackground = true)
@Composable
fun TicTacToeGamePreview() {
    TicTacToeGame()
}
