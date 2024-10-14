package com.example.juegita

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.juegita.ui.theme.JuegITATheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoramaGame(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        val gradientColors = listOf(Color.Blue, Color.Black)
                        Text(
                            text = "Memorama",
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
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        // Imagen de fondo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Añadir padding del Scaffold
                .background(Color.Gray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_2), // Asegúrate de tener esta imagen en drawable
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
                // Texto de título
                Text(
                    text = "Memorama",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1976D2), // Color azul oscuro
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Cuadrícula de cartas (Aquí llamas a tu función CardGrid)
                CardGrid()
            }
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
                painter = painterResource(id = R.drawable.img),
                contentDescription = "Carta Frente",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.img_1),
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
        MemoramaGame(navController = NavController(LocalContext.current))
    }
}
