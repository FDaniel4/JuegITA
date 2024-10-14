package com.example.juegita

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.juegita.ui.theme.JuegITATheme


class Memorama : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JuegITATheme {
                // Llamada a la función principal del memorama
                MemoramaGame()
            }
        }
    }
}

@Composable
fun MemoramaGame() {
    // Imagen de fondo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_2), // Asegúrate de tener esta imagen en tu carpeta drawable
            contentDescription = "Fondo de Memorama",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido del juego
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // texto
            Text(
                text = "Memorama",
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1976D2), // Color azul oscuro
                textAlign = TextAlign.Center
            )

            // Cuadrícula de cartas
            CardGrid()
        }
    }
}

@Composable
fun CardGrid() {
    // Definimos la cuadrícula de 4x3 cartas
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (row in 0 until 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in 0 until 3) {
                    CardButton(cardNumber = (row * 3 + col + 1))
                }
            }
        }
    }
}

@Composable
fun CardButton(cardNumber: Int) {
    var flipped by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(8.dp)
            .clickable { flipped = !flipped }
            .background(
                color = Color.White,
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (flipped) {
            Image(
                painter = painterResource(id = R.drawable.img), // Asegúrate de tener esta imagen en drawable
                contentDescription = "Carta Frente",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.img_1), // Asegúrate de tener esta imagen en drawable
                contentDescription = "Carta Trasera",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MemoramaScreenPreview() {
    JuegITATheme  {
        MemoramaGame()
    }
}
